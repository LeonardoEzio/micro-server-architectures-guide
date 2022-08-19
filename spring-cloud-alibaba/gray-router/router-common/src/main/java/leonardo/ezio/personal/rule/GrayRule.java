package leonardo.ezio.personal.rule;

import java.io.Serializable;

/**
 * @Description : 灰度规则实体
 * @Author : LeonardoEzio
 * @Date: 2022-08-19 15:10
 */
public class GrayRule implements Serializable {

    /**
     * 灰度key  eg : username-按用户名路由，tag-按标签路由， all-全部路由
     * */
    private String routeKey;

    /**
     * 路由匹配规则  eg : equals , like
     * */
    private String matchRule;

    /**
     * 路由匹配值
     * */
    private String matchValue;

    /**
     * 目标路由版本
     * */
    private String targetVersion;

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getMatchRule() {
        return matchRule;
    }

    public void setMatchRule(String matchRule) {
        this.matchRule = matchRule;
    }

    public String getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(String matchValue) {
        this.matchValue = matchValue;
    }

    public String getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }
}
