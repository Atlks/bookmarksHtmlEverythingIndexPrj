package com.attilax.wechatToto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
import org.apache.http.auth.AuthenticationException;

import com.alibaba.fastjson.JSONObject;

//local tt shosye
//   ttShosye_wechatMenuScript
public class ttShosye_wechatMenuScript {

//	private static final  
	//   java -cp  /sqlbek/classes:/lib/*   com.attilax.wechatToto.ttShosye_wechatMenuScript -get
	// java -cp  /sqlbek/classes:/lib/*   com.attilax.wechatToto.ttShosye_wechatMenuScript -create -f G:\0db\tmpTable\ttShosye_menu2019-04-01.194326.json

	public static void main(String[] args) throws Exception {

		final Options options = new Options();
		final Option option_get = new Option("get", false, "Configuration file path");
		final Option option = new Option("create", false, "Configuration file path");
		options.addOption(option);
		options.addOption(option_get);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmdlineCommandLine = parser.parse(options, args);

		String token;

		String tokenStoreFile = "g:\\0db\\tmpTable\\shosye_token.txt";
		try {
			token = WechatUtil.getTokenFromFile(tokenStoreFile);
			WechatUtil.tokenIsOk(token);
		} catch (FileNotFoundException | AuthenticationException e) {

			WXAuthUtil.APPID = "wx87b7c22b03940dc6" + "";
			WXAuthUtil.APPSECRET = "e042ce4ddd154870cceed01b1fbc6ee9";

			JSONObject jsonObject = WXAuthUtil.getAccessToken();
			System.out.println(jsonObject);
			jsonObject.put("gettime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));// );
			FileUtils.write(new File(tokenStoreFile), jsonObject.toJSONString());
			// String access_token= jsonObject.getString("access_token")
			token = jsonObject.getString("access_token");
		}

		if (cmdlineCommandLine.hasOption("create")) {
			String pathname = cmdlineCommandLine.getOptionValue("f");

			create(token, pathname);

		}
		if (cmdlineCommandLine.hasOption("get")) {
			JSONObject MENU = new MenuSeviceImpl().getMenu(token);
			System.out.println(MENU);
			String pathname = "g:\\0db\\tmpTable\\ttShosye_menu{0}.json";
			pathname = MessageFormat.format(pathname,
					new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date()));
			FileUtils.write(new File(pathname), MENU.toJSONString(MENU, true));
		}

		

	}

	private static void create(String token, String pathname) throws IOException {
		String tString = FileUtils.readFileToString(new File(pathname));
		JSONObject mENU_fromFileJsonObject = JSONObject.parseObject(tString);
		System.out.println(tString);
		System.out.println(
				new MenuSeviceImpl().createMenu(mENU_fromFileJsonObject.getString("menu").toString(), token));
	}

}
