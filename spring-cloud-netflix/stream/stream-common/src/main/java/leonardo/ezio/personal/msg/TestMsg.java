package leonardo.ezio.personal.msg;

/**
 * @Description : 测试消息
 * @Author : LeonardoEzio
 * @Date: 2022-01-26 14:50
 */
public class TestMsg {

    private Integer type = 1;

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
        return "TestMsg{" +
                "type=" + type +
                ", body='" + body + '\'' +
                '}';
    }
}
