package com.attilax.wechatToto;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
import org.apache.http.auth.AuthenticationException;

import com.alibaba.fastjson.JSONObject;

//pro script    totoYunsyeto
public class ProdMenuWrite {

//	private static final 
	// java -cp .:/sqlbek/classes:/sqlbek/miniPrjjars/*:/sqlbek/libHttpclient/*
	// com.attilax.wechatToto.ProdMenuWrite /0db_tmpTable/prodTotoYonsyeteo_menu111.json

	public static void main(String[] args) throws Exception {

		String token;

		String tokenStoreFile = "/0db_tmpTable/prod_token.txt";
		try {
			token = WechatUtil.getTokenFromFile(tokenStoreFile);
			WechatUtil.tokenIsOk(token);
		} catch (FileNotFoundException | AuthenticationException e) {

			WXAuthUtil.APPID = "wx923a8fe47e79389e" + "";
			WXAuthUtil.APPSECRET = "cb01a20b5e95caab1815e8f1aff358a3";

			JSONObject jsonObject = WXAuthUtil.getAccessToken();
			System.out.println(jsonObject);
			jsonObject.put("gettime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));// );
			FileUtils.write(new File(tokenStoreFile), jsonObject.toJSONString());
			// String access_token= jsonObject.getString("access_token")
			token = jsonObject.getString("access_token");
		}

 
		String pathname = args[0].trim();

		String tString = FileUtils.readFileToString(new File(pathname));
		JSONObject mENU_fromFileJsonObject = JSONObject.parseObject(tString);
		System.out.println(tString);
		System.out
				.println(new MenuSeviceImpl().createMenu(mENU_fromFileJsonObject.getString("menu").toString(), token));

	}

}
