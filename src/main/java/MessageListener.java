import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventMessage;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;


// 消息的监听器类，继承IcqListener
public class MessageListener extends IcqListener {

    // 这个注解必须加, 用于反射时判断哪些方法是事件方法
    @EventHandler
    // EventPrivateMessage是事件类名，想监听什么时间就监听什么事件，一个方法只能有一个参数
    // 监听所有消息
    public void HandleAllMessage(EventMessage event) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CommandMap commandMap = new CommandMap();

        // 判断是否是指令，不是的话复读
        if (!commandMap.runCommand(event)){
            if ((event.getMessageType().equals("private")&&KuqqPro.SWITCH ==0)
                    ||(event.getMessageType().equals("group")&&KuqqPro.GROUPSWITCH ==0&&KuqqPro.SWITCH ==0)){
                // 加个time，自带的时间什么鬼
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                System.out.println("[qq:"+event.getSenderId()+",Date:"+simpleDateFormat.format(date)+",message:"+event.getMessage()+"]");
                // 复读消息
                event.respond(event.getMessage());
            }
        }
    }

}
