package com.attilax.wechatToto;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.wxpay.sdk.WXPayUtil;

/**
 * 公用网络请求工具类
 */
@Component
public class PayUtil {
    private static Logger log = LoggerFactory.getLogger(PayUtil.class);

    public static String APIKEY; // API密匙

    @Value("${tt.wx.apikey}")
    public void setAppkey(String appkey) {
        APIKEY = appkey;
    }

    public static String DOMAIN; // 信任域名

    @Value("${tt.wx.domain}")
    public void setDomain(String domain) {
        DOMAIN = domain;
    }

    public static String MERCHANT; // 商户号

    @Value("${tt.wx.mchid}")
    public void setMchid(String merchant) {
        MERCHANT = merchant;
    }

    // 企业付款
    private static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    // 查询企业付款
    private static final String GETTRANSFERINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
    public static String ORDERDEPICTS = "课程订单-无描述"; // 订单描述

    public static Map<String, String> unifiedPay(HttpServletRequest request, String openId, float cents, String orderCode)
            throws Exception {
        System.out.println("eric.WXAuthUtil.APIKEY:" + APIKEY);
        System.out.println("eric.MERCHANT:" + MERCHANT);
        System.out.println("eric.DOMAIN:" + DOMAIN);
        log.debug("eric.appid:" + APIKEY);
        log.debug("eric.secret:" + MERCHANT);
        log.debug("eric.domain:" + DOMAIN);

        // 回调地址
        String NotifyUrl = DOMAIN + "/api/wxPay/payCallback";
        return unifiedPay(request, openId, cents, orderCode, NotifyUrl);
    }

    public static Map<String, String> unifiedPay(HttpServletRequest request, String openId, float cents, String orderCode,
            String callbackUrl) throws Exception {
        // 人民币折算：元转换分
        float price = cents * 100;
        // 获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        // 拼接统一下单地址参数
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("appid", WXAuthUtil.APPID);
        paraMap.put("body", ORDERDEPICTS);
        paraMap.put("mch_id", MERCHANT);
        paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
        paraMap.put("openid", openId);
        paraMap.put("out_trade_no", orderCode);// 订单号
        paraMap.put("spbill_create_ip", ip);
        paraMap.put("total_fee", String.valueOf((int) price));
        paraMap.put("trade_type", "JSAPI");
        paraMap.put("notify_url", callbackUrl);
        String sign = WXPayUtil.generateSignature(paraMap, APIKEY);
        paraMap.put("sign", sign);
        System.out.println("eric.paraMap:" + paraMap);
        log.debug("eric.paraMap:" + paraMap);

        String xml = WXPayUtil.mapToXml(paraMap);// 将所有参数(map)转xml格式
        System.out.println("eric.WXPayUtil.mapToXml.xml:" + xml);
        log.debug("eric.WXPayUtil.mapToXml.xml:" + xml);

        // 统一下单
        String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String xmlStr = WXAuthUtil.postXML(unifiedorder_url, xml);
        System.out.println("eric.xmlStr:" + xmlStr);
        log.debug("eric.xmlStr:" + xmlStr);

        String prepay_id = "";// 预支付id
        if (xmlStr.indexOf("SUCCESS") != -1) {
            Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
            System.out.println("eric.WXPayUtil.xmlToMap:" + map);
            log.debug("eric.WXPayUtil.xmlToMap:" + map);
            prepay_id = map.get("prepay_id");
        }
        Map<String, String> payMap = new HashMap<String, String>();
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        payMap.put("appId", WXAuthUtil.APPID);
        payMap.put("timeStamp", timestamp);
        payMap.put("nonceStr", WXPayUtil.generateNonceStr());
        payMap.put("signType", "MD5");
        payMap.put("package", "prepay_id=" + prepay_id);
        String paySign = WXPayUtil.generateSignature(payMap, APIKEY);
        payMap.put("paySign", paySign);
        return payMap;
    }

    public static String returnXML(String returnCode) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    /**
     * 企业付款到零钱
     * @throws Exception
     */
    public static String transfers(HttpServletRequest request) throws Exception {
        String openId = "oRMVFv8zhSH--EhJiu3z9G3kNX-o";// 用户openid

        Map<String, String> params = new HashMap<String, String>();
        params.put("mch_appid", WXAuthUtil.APPID);
        params.put("mchid", MERCHANT);
        params.put("nonce_str", WXPayUtil.generateNonceStr());
        String partnerTradeNo = "";// 订单号
        params.put("partner_trade_no", partnerTradeNo);
        params.put("openid", openId);
        params.put("check_name", "NO_CHECK");
        params.put("amount", "100");
        params.put("desc", "唐豆提现");
        // 获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        params.put("spbill_create_ip", ip);

        String sign = WXPayUtil.generateSignature(params, APIKEY);
        params.put("sign", sign);
        String strUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        // 提现
        String transfers = HttpUtil.requestWithCert(strUrl, params, 8000, 10000, MERCHANT);
        log.info("提现结果:" + transfers);
        System.out.println("提现结果:" + transfers);

        Map<String, String> map = WXPayUtil.xmlToMap(transfers);
        String return_code = map.get("return_code");
        String result_code = null;
        if (("SUCCESS").equals(return_code)) {
            result_code = map.get("result_code");
            if (("SUCCESS").equals(result_code)) {
                // 提现成功
                System.out.println("提现成功");
            } else {
                // 提现失败
                System.out.println("提现失败");
            }
        }
        return result_code;
    }

    /**
     * 查询企业付款到零钱
     * @throws Exception
     */
    public String transferInfo() throws Exception {
        String partner_trade_no = "partner_trade_no"; //
        Map<String, String> params = new HashMap<String, String>();
        params.put("nonce_str", WXPayUtil.generateNonceStr());
        params.put("partner_trade_no", partner_trade_no);
        params.put("mch_id", MERCHANT);
        params.put("appid", WXAuthUtil.APPID);
        params.put("sign", WXPayUtil.generateSignature(params, APIKEY));
        String strUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
        // 查询付款
        String transfers = HttpUtil.requestWithCert(strUrl, params, 8000, 10000, MERCHANT);
        Map<String, String> map = WXPayUtil.xmlToMap(transfers);
        String return_code = map.get("return_code");
        String result_code = null;
        if (("SUCCESS").equals(return_code)) {
            result_code = map.get("result_code");
            if (("SUCCESS").equals(result_code)) {
                // 提现成功
            } else {
                // 提现失败
            }
        }
        return result_code;
    }

}
