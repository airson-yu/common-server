package cc.airson.common.server.feature.wx;

public class UrlData {
    String corpId;
    String corpSecret;
    String getTokenUrl;
    String sendMessageUrl;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public void setGetTokenUrl(String corpid, String corpsecret) {
        this.getTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
    }

    public String getGetTokenUrl() {
        return getTokenUrl;
    }

    public String getSendMessageUrl() {
        sendMessageUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
        return sendMessageUrl;
    }

}