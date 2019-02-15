import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.exceptions.HttpServerStartFailedException;

public class KuqqPro {
    public static int SWITCH=0; // 总开关
    public static int GROUPSWITCH = 0; // 群聊开关

    public static void main(String[] args) {

        // 创建机器人对象( 信息发送 URL, 发送端口, 接收端口, 是否 DEBUG )
        PicqBotX bot = new PicqBotX("127.0.0.1",5700,5701,false);
        try {
            bot.getEventManager()
                    .registerListener(new Filter()) // 注册过滤器
                    .registerListener(new MessageListener()) // 注册监听器
            ;
            // 开启指令集，只能在前面，我也很无奈啊
            bot.enableCommandManager("bot -","!","！","~"," ");
            // 启动机器人，会占用线程所以放到最后
            bot.startBot();
        }catch (HttpServerStartFailedException | IllegalAccessException|InstantiationException e){
            e.printStackTrace(); // 启动失败，结束程序
        }
    }

    public static int getSWITCH() {
        return SWITCH;
    }

    public static void setSWITCH(int SWITCH) {
        KuqqPro.SWITCH = SWITCH;
    }

    public static int getGROUPSWITCH() {
        return GROUPSWITCH;
    }

    public static void setGROUPSWITCH(int GROUPSWITCH) {
        KuqqPro.GROUPSWITCH = GROUPSWITCH;
    }
}
