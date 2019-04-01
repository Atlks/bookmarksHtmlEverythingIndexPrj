package com.attilax.wechatToto;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.tt.core.function.config.Contants;

public class JSAPITicketUtil {
    public static String getJsapi_ticket(String access_token) throws IOException {
        System.out.println("access_token===" + access_token);
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
        JSONObject jsonObject = HttpUtil.httpRequest(url, "GET", null);
        System.out.println(jsonObject);
        String ticket = jsonObject.getString("ticket");
        Contants.JSAPI_TICKET = jsonObject.getString("ticket");
        Contants.ticket_expires_in = jsonObject.getInteger("expires_in");
        return ticket;
    }

    public static String getSignature(String url, String timeStamp, String nonceStr) {
        // 所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1：
        String signValue = "jsapi_ticket=" + Contants.JSAPI_TICKET + "&noncestr=" + nonceStr + "×tamp=" + timeStamp + "&url=" + url;
        String signature = DigestUtils.sha1Hex(signValue);
        return signature;
    }
}
