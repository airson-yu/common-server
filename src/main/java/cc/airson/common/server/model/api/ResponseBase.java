package cc.airson.common.server.model.api;

import cc.airson.common.server.config.ResultCodeBase;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * REST API Response基类
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
public class ResponseBase {
    /*
     * 返回码
     */
    private int    code;
    private String message;
    private Object data;

    public ResponseBase(ResultCodeBase code) {
        this.code = code.code();
        this.message = code.message();
    }

    public ResponseBase(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        // XXX 如果直接返回对象，是由spring-mvc.xml统一配置序列化参数，不会执行到这里，直接打印才会执行。
        return JSONObject.toJSONString(this, SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero);
        //return ToStringBuilder.reflectionToString(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
