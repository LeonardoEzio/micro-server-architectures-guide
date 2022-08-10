package leonardo.ezio.personal.dto;

import java.io.Serializable;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 17:40
 */
public class MsgDto implements Serializable {

    /**
     * 消息类型
     * */
    private String msgType;

    /**
     * 消息内容
     * */
    private String msgContent;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
