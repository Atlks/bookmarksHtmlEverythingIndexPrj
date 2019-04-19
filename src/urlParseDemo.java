import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.tools.ant.taskdefs.condition.And;
import org.omg.CORBA.NameValuePairHelper;
import org.omg.DynamicAny.NameValuePairSeqHelper;

 

public class urlParseDemo {
	
	public static void main(String[] args) throws URISyntaxException, ParseException, IOException {
		
		//uri vs url diff ,is uri is Onlystr,And urlAnd  Include res op as open
		String url = "   http:/ /1?get=access_token";		
		url=url.trim();
		URI url2 = new URI(url);
		System.out.println(url2.getQuery());	
	
		//获取键值对
	java.util.List<NameValuePair> list=	new	URIBuilder(url2).getQueryParams();
	System.out.println(list);
	
	//增加键值对
	//new	URIBuilder().addParameter(param, value)
	
//	去除键值
	//list.remove(o)
	
	
	//链接为query str模式	
	//转换为键值对字符串
	UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list); 
	System.out.println(	EntityUtils.toString(urlEncodedFormEntity) );
		// url2.toURI().get

		
		
	}

}
