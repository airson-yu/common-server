package cc.airson.common.server.feature.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信通知消息
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
@Component
public class QYWeChatAPI {

    private static Logger logger = LoggerFactory.getLogger(QYWeChatAPI.class);

    private             CloseableHttpClient httpClient;
    private             HttpPost            httpPost;//用于提交登陆数据
    private             HttpGet             httpGet;//用于获得登录后的页面
    public static final String              CONTENT_TYPE = "Content-Type";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
    private static final String corpId     = "123";
    private static final String corpSecret = "123";


    public String getToken(String corpId, String corpSecret) throws IOException {
        UrlData uData = new UrlData();
        uData.setGetTokenUrl(corpId, corpSecret);
        String resp = toAuth(uData.getGetTokenUrl());//就是按照GET方式拼接了字符串得到url
        JSONObject rspJson = JSON.parseObject(resp);
        return rspJson.getString("access_token");
    }

    protected String toAuth(String get_Token_Url) throws IOException {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(get_Token_Url);
        logger.debug("wx_toAuth_url:{}", get_Token_Url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        logger.debug("wx_toAuth_rsp:{}", response.toString());
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            //System.out.println(response.getAllHeaders());
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return resp;
    }


    /**
     * 创建POST BODY
     */
    public String createPostData(String touser, String msgtype, int agent_id, String contentKey, String contentValue) {
        WeChatData weChatData = new WeChatData();
        weChatData.setTouser(touser);
        weChatData.setAgentid(agent_id);
        weChatData.setMsgtype(msgtype);
        Map<Object, Object> content = new HashMap<>();
        content.put(contentKey, contentValue + "\n--------\n" + df.format(new Date()));
        weChatData.setText(content);
        String data = JSON.toJSONString(weChatData);
        logger.debug("wx_createPostData:{}", data);
        return data;
    }

    /**
     * POST请求
     */
    public String post(String charset, String contentType, String url, String data, String token) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String wholeUrl = url + token;
        httpPost = new HttpPost(wholeUrl);
        logger.debug("wx_post_url:{}", wholeUrl);
        httpPost.setHeader(CONTENT_TYPE, contentType);
        httpPost.setEntity(new StringEntity(data, charset));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        logger.debug("wx_post_rsp:{}", response.toString());
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return resp;
    }

    public boolean sentNoticeViaWechat(String users, String text) {
        if (StringUtils.isEmpty(users)) {
            users = "aa|bb|cc";
            //users = "airson";
        }
        String token = null;
        try {
            token = getToken(corpId, corpSecret);
        } catch (IOException e) {
            logger.error("sentNoticeViaWechat getToken error:{}", e.getMessage());
            e.printStackTrace();
        }
        String postData = createPostData(users, "text", 1000003, "content", text);
        try {
            String resp = post("utf-8", CONTENT_TYPE, (new UrlData()).getSendMessageUrl(), postData, token);
        } catch (IOException e) {
            logger.error("sentNoticeViaWechat post error:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
