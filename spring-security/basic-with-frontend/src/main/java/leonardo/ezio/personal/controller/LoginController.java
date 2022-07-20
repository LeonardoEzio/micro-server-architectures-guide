package leonardo.ezio.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description : 登录业务
 * @Author : LeonardoEzio
 * @Date: 2022-07-19 14:53
 */
@Controller
public class LoginController {

    /**
    * 跳转登录页面
    *
    * @name toLoginPage
    * @param
    * @return  * @return {@link String }
    * @date  2022/7/19 14:54
    **/
    @RequestMapping("toLoginPage")
    public String toLoginPage(){
        return "login";
    }
}
