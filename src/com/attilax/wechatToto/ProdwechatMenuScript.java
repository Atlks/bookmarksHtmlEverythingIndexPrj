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
public class ProdwechatMenuScript {

//	private static final 
	//  java -cp .:/sqlbek/classes:/sqlbek/miniPrjjars/*:/sqlbek/libHttpclient/*  com.attilax.wechatToto.ProdwechatMenuScript 

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

		// System.out.println(WXAuthUtil.getAccessToken());

		// String
		// token="20_NzEqY5k1bj64tguXl3oZb-QkVy4WcGowYf7R8ERXcu3raN2lVYv_xz_njX_ecp4T81VBceqPr0feNxE5rfs3xb_pe_loPik4AXLv30dQlSediaLj6_bq9y5ak4Rg3_59mtmikF15badSVCozSYPiAJANTK";
		//
		// {"access_token":"20_NzEqY5k1bj64tguXl3oZb-QkVy4WcGowYf7R8ERXcu3raN2lVYv_xz_njX_ecp4T81VBceqPr0feNxE5rfs3xb_pe_loPik4AXLv30dQlSediaLj6_bq9y5ak4Rg3_59mtmikF15badSVCozSYPiAJANTK","expires_in":7200}
		JSONObject MENU = new MenuSeviceImpl().getMenu(token);
	//	FileUtils.write(new File("g:\\0db\\tmpTable\\preTotoIuwen_menu.json"), MENU.toJSONString(MENU, true));
		System.out.println(MENU);		
		String pathname = "/0db_tmpTable/prodTotoYonsyeteo_menu{0}.json";
		pathname = MessageFormat.format(pathname, new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date()));
		FileUtils.write(  new File(pathname),MENU.toJSONString(MENU, true));
//		String tString=FileUtils.readFileToString(new File("/0db_tmpTable/prodTotoIuwen_menu.json"));
//		JSONObject mENU_fromFileJsonObject=JSONObject.parseObject(tString);
//		System.out.println(tString+);
	//	System.out.println( new MenuSeviceImpl().createMenu(mENU_fromFileJsonObject.getString("menu").toString(), token));
		
		
		 
	}

}
