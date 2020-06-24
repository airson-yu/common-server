package cc.airson.common.server.model.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * REST API Request基类
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
public class RequestBase {

    /* User ID */
    private Long uid;

    /* Access Token */
    private String token;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
