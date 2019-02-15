import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.local.EventLocalSendMessage;

// 监听所有消息，进行消息的替换过滤
public class Filter extends IcqListener {
    @EventHandler
    // 监听所有发送消息的事件
    public void onAllLocalMessageEvent(EventLocalSendMessage event){
        // 获取消息
        String message = event.getMessage();
        // 进行处理替换
        message = message.replace("?","!").replace("？","!").replace("吗","");
        // 进行屏蔽

        String noRepeat[] = {"屎","sb","傻逼","辣鸡"};
        for (String s:noRepeat){
            if (message.contains(s)){
                message = "文明社会需要你我共同建设[CQ:image,file=5F773A04B713EC376E50BE2DC923CACC.jpg]";
            }
        }
        // 空回复
        String RepeatNULL[] = {"点歌"};
        for (String s:RepeatNULL){
            if (message.contains(s)){
                message = ""; // 消息为空不会发送
            }
        }
        event.setMessage(message);
    }
}
