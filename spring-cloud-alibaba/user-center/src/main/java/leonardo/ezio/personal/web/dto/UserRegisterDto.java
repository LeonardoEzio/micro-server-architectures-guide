package leonardo.ezio.personal.web.dto;

import java.io.Serializable;

/**
 * @Description : 用户注册实体
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 12:22
 */
public class UserRegisterDto implements Serializable {

    private String userName;

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
