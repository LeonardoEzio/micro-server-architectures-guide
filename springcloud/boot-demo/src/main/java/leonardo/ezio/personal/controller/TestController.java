package leonardo.ezio.personal.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-08-19 18:43
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "success !!!";
    }

    @RequestMapping(value = "/testTwo",consumes = MediaType.TEXT_HTML_VALUE)
    public String testTwo(HttpServletRequest request)throws Exception{
        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        String str =sb.toString();
        return str;
    }
}