package aOPtool;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class testPreEnvi_urlTestTool {
	static Logger logger = Logger.getLogger(testPreEnvi_urlTestTool.class);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		testUrl("http://app.chinesesr.com");

		// String url =
		// "http://101.132.148.11:7000/api/api/cCourse/getIsFreeCoursesApi";

		testUrl("http://api.chinesesr.com/api/api/cCourse/getIsFreeCoursesApi");
		testUrl("http://admin.chinesesr.com/admin/");

	}

	private static void testUrl(String url) throws URISyntaxException, IOException, ClientProtocolException {
		logger.info("----url:" + url);

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 执行请求
		CloseableHttpResponse response = httpclient.execute(new HttpGet(new URIBuilder(url).build()));

		// 判断返回状态是否为200
		logger.info("------response.getStatusLine().getStatusCode()::" + response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() == 200) {

		}
		logger.info("-------response.toString)::" + EntityUtils.toString(response.getEntity(), "UTF-8"));
	}

}
