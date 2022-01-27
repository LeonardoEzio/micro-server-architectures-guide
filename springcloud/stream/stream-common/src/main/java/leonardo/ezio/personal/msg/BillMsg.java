package leonardo.ezio.personal.msg;

/**
 * @Description : 账单消息
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:29
 */
public class BillMsg {

    private Integer type = 3;

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
        return "BillMsg{" +
                "type=" + type +
                ", body='" + body + '\'' +
                '}';
    }
}
