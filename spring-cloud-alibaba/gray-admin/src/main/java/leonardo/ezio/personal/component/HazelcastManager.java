package leonardo.ezio.personal.component;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import leonardo.ezio.personal.common.GrayConstant;
import leonardo.ezio.personal.rule.GrayRule;
import leonardo.ezio.personal.web.dto.GrayRuleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-08-19 15:16
 */
@Component
public class HazelcastManager {

    private static final HazelcastInstance HAZELCAST_INSTANCE;

    static {
        HAZELCAST_INSTANCE = Hazelcast.newHazelcastInstance();
    }

    public static void addRule(GrayRuleDto grayRuleDto){
        GrayRule rule = new GrayRule();
        BeanUtils.copyProperties(grayRuleDto,rule);
        HAZELCAST_INSTANCE.getList(GrayConstant.GRAY_RULE).add(rule);
    }
}
