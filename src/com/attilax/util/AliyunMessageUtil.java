package com.attilax.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Created on 17/6/7. 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可) 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8 国际短信发送请勿参照此DEMO
 */
public class AliyunMessageUtil {

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAIMaVxNlD5pgSk";
	static final String accessKeySecret = "6xBXmGbFsr42sgURwrX1JWLVJWFu3L";

	public static SendSmsResponse sendSms(Map<String, String> paramMap) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(paramMap.get("phoneNumber"));
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(paramMap.get("msgSign"));
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(paramMap.get("templateCode"));
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(paramMap.get("jsonContent"));

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

	/**
	 * 生成随机数
	 * 
	 * @param num 位数
	 * @return
	 */
	public static String createRandomNum(int num) {
		String randomNumStr = "";
		for (int i = 0; i < num; i++) {
			int randomNum = (int) (Math.random() * 10);
			randomNumStr += randomNum;
		}
		return randomNumStr;
	}

	public static void main(String[] args) throws ClientException {
		String phoneNumber = "18821766710";
		String randomNum = createRandomNum(6);
		String jsonContent = "{\"code\":\"" + randomNum + "\"}";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("msgSign", "唐唐云学堂");
		// paramMap.put("templateCode", "SMS_150495957");

		paramMap.put("templateCode", "SMS_150495959");
		paramMap.put("jsonContent", jsonContent);
		SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
		System.out.println("已发送");
		System.out.println(JSON.toJSON(sendSmsResponse));
		// {"code":"OK","requestId":"27392149-150C-440B-802A-590C422AFB60","bizId":"422313255463283685^0","message":"OK"}

		if (!(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK"))) {
			if (sendSmsResponse.getCode() == null) {
				System.out.println(sendSmsResponse.getCode());
			}
			if (!sendSmsResponse.getCode().equals("OK")) {
				System.out.println(sendSmsResponse.getCode() + "");
			}
		}
	}
}
