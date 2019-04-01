package com.attilax.wechatToto;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
import org.apache.http.auth.AuthenticationException;

import com.alibaba.fastjson.JSONObject;

//pre script totoiuwen 
public class wechatMenuScript {

//	private static final 
	//

	public static void main(String[] args) throws Exception {

		String token;

		String tokenStoreFile = "g:\\0db\\tmpTable\\token.txt";
		try {
			token = WechatUtil.getTokenFromFile(tokenStoreFile);
			WechatUtil.tokenIsOk(token);
		} catch (FileNotFoundException | AuthenticationException e) {

			WXAuthUtil.APPID = "wx8ab1db22113b030d" + "";
			WXAuthUtil.APPSECRET = "21189c9ca7daefacc8d2a4177eb90fc6";

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
		FileUtils.write(new File("g:\\0db\\tmpTable\\preTotoIuwen_menu.json"), MENU.toJSONString(MENU, true));
		System.out.println(MENU);
		 
	}

}
