package leonardo.ezio.personal.msg;

/**
 * @Description : 提醒消息实体
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:29
 */
public class AlarmMsg {

    private Integer type = 2;

    private String body;

    public Integer getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AlarmMsg{" +
                "type=" + type +
                ", body='" + body + '\'' +
                '}';
    }
}
