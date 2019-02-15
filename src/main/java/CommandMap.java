import cc.moecraft.icq.event.events.message.EventMessage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 构造一个map，用来存放指令和执行的命令
 */
public class CommandMap{
    Map<String,String> commandMap = new LinkedHashMap<>();// 这个map顺序是对的
    // 执行方法
    public boolean runCommand(EventMessage event) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        boolean flag = false;

        commandMap.put("菜单","menu");
        commandMap.put("开启机器人！","OpenRobot");
        commandMap.put("关闭机器人！","CloseRobot");
        commandMap.put("开启群聊！","OpenGroup");
        commandMap.put("关闭群聊！","CloseGroup");
        commandMap.put("功能开启情况","Status");
            // 判断是否是指令
        for (Map.Entry<String,String> entry:commandMap.entrySet()){
            if (entry.getKey().equals(event.getMessage())){
                CommandMap commandMap1 = new CommandMap();
                // 反射执行相应的方法
                commandMap1.reflect(entry.getValue(), event);
                flag = true;
            }
        }
        System.out.println("map长度为："+commandMap.size());
        return flag;
    }

    // 反射
    public void reflect(String s, EventMessage event) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 指定加载的类
        Class<?> c = CommandMap.class;
        // 通过方法名寻找相应的方法，并赋给一个方法对象
        Method method = c.getMethod(s,EventMessage.class);
        // 调用方法
        method.invoke(c.newInstance(),event);
    }
    // 菜单
    public void menu(EventMessage event){
        System.out.println("菜单");
        for (Map.Entry<String,String> entry:commandMap.entrySet()){
            System.out.println(entry.getKey());
        }

    }

    // 打开机器人！
    public void OpenRobot(EventMessage event){
        if (event.getSenderId()==360080501){
            KuqqPro.setSWITCH(0);
            KuqqPro.setGROUPSWITCH(0);
            event.respond("我来啦！[CQ:image,file=DFB73DFFB0E5160CE10EE310D337D26A.gif]");
        }
    }

    // 关闭机器人！
    public void CloseRobot(EventMessage event){
        if (event.getSenderId()==360080501){
            KuqqPro.setSWITCH(1);
            KuqqPro.setGROUPSWITCH(1);
            event.respond("我先出去玩啦！[CQ:image,file=DFB73DFFB0E5160CE10EE310D337D26A.gif]");
        }
    }

    // 打开群聊！
    public void OpenGroup(EventMessage event){
        if (event.getSenderId()==360080501){
            KuqqPro.setGROUPSWITCH(0);
            event.respond("我来啦！[CQ:image,file=DFB73DFFB0E5160CE10EE310D337D26A.gif]");
        }

    }

    // 关闭群聊！
    public void CloseGroup(EventMessage event){
        if (event.getSenderId()==360080501){
            KuqqPro.setGROUPSWITCH(1);
            event.respond("我不在群聊玩了，想我了可以私聊哦[CQ:image,file=8C23945BF41FED6FC92052AEE276A2A5.jpg]");
        }

    }

    // 功能开启情况
    public void Status(EventMessage event){
        String s1; // 总状态
        String s2; // 群聊状态
        if (KuqqPro.getSWITCH()==0){
            s1 = "私聊:open!  ";
        }else {
            s1 = "私聊:off~"  ;
        }
        if (KuqqPro.getGROUPSWITCH()==0){
            s2 = "群聊:open!";
        }else {
            s2 = "群聊:off!";
        }
        event.respond(s1+s2);
    }
}
