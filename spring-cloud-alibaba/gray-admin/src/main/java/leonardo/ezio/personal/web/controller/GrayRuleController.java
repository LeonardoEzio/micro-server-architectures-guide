package leonardo.ezio.personal.web.controller;

import leonardo.ezio.personal.component.HazelcastManager;
import leonardo.ezio.personal.web.dto.GrayRuleDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 灰度规则控制器
 * @Author : LeonardoEzio
 * @Date: 2022-08-19 15:06
 */
@RestController
@RequestMapping("rule")
public class GrayRuleController {

    @RequestMapping("publish")
    public String publish(@RequestBody GrayRuleDto grayRuleDto){
        HazelcastManager.addRule(grayRuleDto);
        return "success";
    }
}
