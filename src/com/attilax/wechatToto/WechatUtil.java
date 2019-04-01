package com.attilax.wechatToto;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.auth.AuthenticationException;

import com.alibaba.fastjson.JSONObject;

public class WechatUtil {

	public static boolean tokenIsOk(String token) throws AuthenticationException {
		System.out.println("WechatUtil.tokenIsOk");
		System.out.println( new	MenuSeviceImpl().getMenu(token));
		return true;
	}

	public static String getTokenFromFile(String f) throws IOException {
		
				String t=FileUtils.readFileToString(new File(f));
				JSONObject jsonObject =JSONObject.parseObject(t);
		return jsonObject.getString("access_token");
	}

}
