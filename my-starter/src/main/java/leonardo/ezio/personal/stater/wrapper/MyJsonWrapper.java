package leonardo.ezio.personal.stater.wrapper;

import com.alibaba.fastjson.JSON;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-06-11 15:09
 */
public class MyJsonWrapper {

    private String prefix;

    private String suffix;

    public String wrapper(Object obj){
        return prefix + JSON.toJSONString(obj) + suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
