package aOPtool;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.url.SingleConnectionUrl;

public class MysqlConnUrlParse {

	public static void main(String[] args) {
		// String connString;
			String mysqlConnUrl = "jdbc:mysql://47.100.12.36:3306/tt_pre?user=root&password=hhh";
			
		ConnectionUrlParser connStringParser = ConnectionUrlParser.parseConnectionString(mysqlConnUrl);
		System.out.println(connStringParser);
	//	com.mysql.cj.conf.ConnectionUrlParser@246b179d :: 
//		{scheme: "jdbc:mysql:", 
//			authority: "47.100.12.36:3306", 
//			path: "tt_pre", 
//			query: "user=root&password=123456&userinfo=root:123456", 
//			parsedHosts: null, parsedProperties: null}

		
		
		
		
		SingleConnectionUrl ConnectionUrl2=new SingleConnectionUrl(ConnectionUrlParser.parseConnectionString(mysqlConnUrl), null);
System.out.println(ConnectionUrl2);
	}

}
