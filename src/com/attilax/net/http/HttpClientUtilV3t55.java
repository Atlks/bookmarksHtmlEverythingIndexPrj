/**
 * Copyright(C), 2013-2014
 *		 Shenzhen Coordinates Software Development Co., Ltd.
 * com.cnhis.cloudhealth.open.service.plugin.weixin.util.HttpRequest.java
 */
package com.attilax.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;
//import org.json.JSONObject;

import com.alibaba.fastjson.JSONObject;
//import com.attilax.core.Consumer;
import com.attilax.util.ExUtil;
//import com.attilax.web.SSLClient;

/**
 * 
 * v3 s521 add post put ati v4 add get ret bytearr
 * 
 * @Description: TODO(类功能描�?)
 * @Package com.cnhis.cloudhealth.open.service.plugin.weixin.util
 * @ClassName HttpRequest
 * @author Administrator
 * @date 2016�?6�?23�? 下午3:31:03
 * @version 版本�? V1.0.0
 */

public class HttpClientUtilV3t55 {

	/**
	 * only params not name pair
	 * 
	 * @param url
	 * @param param
	 * @param responseParseEncode
	 * @return
	 */
	public String httppost(String url, String param, String responseParseEncode) {

		// HttpEntity
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		HttpPost HttpPost1_HttpUriRequest = new HttpPost(url);

		// 构建消息实体
		StringEntity entity = new StringEntity(param, Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		// 发�?�Json格式的数据请�?
		entity.setContentType("application/json");

		HttpPost1_HttpUriRequest.setEntity(entity);
		// httpCilent.execute(httpGet);
		byte[] arr = execute_only200ok(httpCilent, HttpPost1_HttpUriRequest);

		try {
			return new String(arr, responseParseEncode);
		} catch (UnsupportedEncodingException e) {
			ExUtil.throwExV2(e);
		}
		ExUtil.throwEx("ex");
		return responseParseEncode;
	}

	/**
	 * only params not name pair
	 * 
	 * @param url
	 * @param param
	 * @param responseParseEncode
	 * @return
	 */
	public String httpput(String url, String param, String responseParseEncode) {

		// HttpEntity
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		HttpPut HttpPut1 = new HttpPut(url);

		// 构建消息实体
		StringEntity entity = new StringEntity(param, Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		// 发�?�Json格式的数据请�?
		entity.setContentType("application/json");

		HttpPut1.setEntity(entity);
		// httpCilent.execute(httpGet);
		byte[] arr = execute_only200ok(httpCilent, HttpPut1);

		try {
			return new String(arr, responseParseEncode);
		} catch (UnsupportedEncodingException e) {
			ExUtil.throwExV2(e);
		}
		ExUtil.throwEx("ex");
		return responseParseEncode;
	}

	public String httpput(String url, Map map, String responseParseEncode) {
		byte[] arr = httpput(url, map);
		try {
			return new String(arr, responseParseEncode);
		} catch (UnsupportedEncodingException e) {
			ExUtil.throwExV2(e);
		}
		ExUtil.throwEx("ex");
		return responseParseEncode;
	}

	/**
	 * ati
	 * 
	 * @param url
	 * @return
	 */
	public byte[] httpput(String url, Map map) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();// Creates
																		// CloseableHttpClient
																		// instance
																		// with
																		// default
																		// configuration.
		HttpPut HttpPut1 = new HttpPut(url);

		HttpEntity urlEncodedFormEntity = null;
		try {
			urlEncodedFormEntity = asList_NameValuePair(map);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpPut1.setEntity(urlEncodedFormEntity);
		// httpCilent.execute(httpGet);
		return execute_only200ok(httpCilent, HttpPut1);
	}

	private UrlEncodedFormEntity asList_NameValuePair(Map map) throws UnsupportedEncodingException {
		List<NameValuePair> values_ListNameValuePair = new ArrayList<NameValuePair>();

		// values.add(new BasicNameValuePair("id", "1"));

		// values.add(new BasicNameValuePair("name", "xiaohong"));
		addPostParams(map, values_ListNameValuePair);
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(values_ListNameValuePair, HTTP.UTF_8);
		return urlEncodedFormEntity;
	}

	private byte[] execute_only200ok(CloseableHttpClient httpCilent, HttpUriRequest HttpUriRequest1) {
		byte[] srtResult;

		HttpResponse httpResponse;
		try {
			httpResponse = httpCilent.execute(HttpUriRequest1);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200 || statusCode == 201) {

				HttpEntity entity = httpResponse.getEntity();

			 
				srtResult = EntityUtils.toByteArray(entity);

				return srtResult;
			} else if (statusCode == 400) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 400");
			} else if (statusCode == 500) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 500");
			} else
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == " + statusCode);

		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} // 获得返回的结�?

	}

//	public String doPost(String url, Map<String, String> map, String charset) {
//		HttpClient httpClient = null;
//		HttpPost httpPost = null;
//		String result = null;
//		try {
//			httpClient = new SSLClient();
//			httpPost = new HttpPost(url);
//			// 设置参数
//			List<NameValuePair> list = new ArrayList<NameValuePair>();
//			addPostParams(map, list);
//			if (list.size() > 0) {
//				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
//				httpPost.setEntity(entity);
//			}
//			HttpResponse response = httpClient.execute(httpPost);
//			if (response != null) {
//				HttpEntity resEntity = response.getEntity();
//				if (resEntity != null) {
//					result = EntityUtils.toString(resEntity, charset);
//				}
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return result;
//	}

	private void addPostParams(Map<String, String> map, List<NameValuePair> list) {
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {

			Entry<String, Object> elem = (Entry<String, Object>) iterator.next();
			list.add(new BasicNameValuePair(elem.getKey(), elem.getValue().toString()));
		}

	}

	public void http_process(HttpResponse httpResponse, Consumer<HttpEntity> Consumer1) {

		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// httpResponse.getEntity().getContent().
			try {
				Consumer1.accept((httpResponse.getEntity()));
			} catch (Exception e) {
				ExUtil.throwExV2(e);
			}

		} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
			throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 400");
		} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
			throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 500");
		}
		//
		// } catch (ParseException | IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// throw new RuntimeException(e);
		// } // 获得返回的结�?
		// throw new RuntimeException(" err");
	}

//	public void httpget_hiperf_outputStram(String url, final OutputStream ots) {
//
//		CloseableHttpClient httpCilent = HttpClients.createDefault();// Creates
//																		// CloseableHttpClient
//																		// instance
//																		// with
//																		// default
//																		// configuration.
//		HttpGet httpGet = new HttpGet(url);
//
//		// httpCilent.execute(httpGet);
//		byte[] srtResult;
//
//		final HttpResponse httpResponse;
//
//		try {
//			httpResponse = httpCilent.execute(httpGet);
//			http_process(httpResponse, new Consumer<HttpEntity>() {
//
//				@Override
//				public void accept(HttpEntity t) throws Exception {
//					InputStream is = httpResponse.getEntity().getContent();
//					IOUtils.copy(is, ots);
//
//				}
//			});
//
//		} catch (ParseException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} // 获得返回的结�?
//		throw new RuntimeException(" err");
//	}

	public byte[] httpget_retBytearr(String url) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();// Creates
																		// CloseableHttpClient
																		// instance
																		// with
																		// default
																		// configuration.
		HttpGet httpGet = new HttpGet(url);

		// httpCilent.execute(httpGet);
		byte[] srtResult;

		HttpResponse httpResponse;
		try {
			httpResponse = httpCilent.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				srtResult = EntityUtils.toByteArray(httpResponse.getEntity());

				return srtResult;
			} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 400");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 500");
			}

		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} // 获得返回的结�?
		throw new RuntimeException(" err");
	}
	@Deprecated
	public String httpget(String url, String enc) {
		return enc;
		// httpget_retBytearr

	}

	public static String getContentCharSet(final HttpEntity entity) throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();

				}
			}
		}

		if (charset == null)
			throw new RuntimeException(" charset is null");
		return charset;
	}
	public static Logger logger = Logger.getLogger(HttpClientUtils.class);
	public String httpget_autoEncode(String url) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();// Creates
																		// CloseableHttpClient
																		// instance
																		// with
																		// default
																		// configuration.
		HttpGet httpGet = new HttpGet(url);

		// httpCilent.execute(httpGet);
		String srtResult = "";

		HttpResponse httpResponse;
		try {
			httpResponse = httpCilent.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				return getRzt(httpResponse);
			} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 400");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				logger.error(getRzt(httpResponse));
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 500");
			}

		} catch (ParseException | IOException e) {

			throw new RuntimeException(e);
		} // 获得返回的结�?
		throw new RuntimeException(" err");
	}

	private String getRzt(HttpResponse httpResponse) throws IOException, UnsupportedEncodingException {
		HttpEntity entity = httpResponse.getEntity();
		String charset = getContentCharSet(entity);
		byte[] srtResult_bytes = EntityUtils.toByteArray(entity);

		return new String(srtResult_bytes, charset);
	}
	public static String  get(String url) throws  Exception{
		// 执行get请求.
					CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
					// 获取响应实体
					String contentCharSet = EntityUtils.getContentCharSet(response.getEntity());
//					if(contentCharSet==null)
//					{
//=						//contentCharSet=
//					}
					String html = EntityUtils.toString(response.getEntity(),contentCharSet);
					return html;
	}
	public static String  get(String url,String encode) throws  Exception{
		// 执行get请求.
					CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
					// 获取响应实体
					String contentCharSet = EntityUtils.getContentCharSet(response.getEntity());
//					if(contentCharSet==null)
//					{
//=						//contentCharSet=
//					}
					String html = EntityUtils.toString(response.getEntity(),encode);
					return html;
	}
	
	
	@Deprecated
	public String httpget(String url) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();// Creates
																		// CloseableHttpClient
																		// instance
																		// with
																		// default
																		// configuration.
		HttpGet httpGet = new HttpGet(url);

		// httpCilent.execute(httpGet);
		String srtResult = "";

		HttpResponse httpResponse;
		try {
			httpResponse = httpCilent.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				srtResult = EntityUtils.toString(httpResponse.getEntity());

				return srtResult;
			} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 400");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 500");
			}

		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} // 获得返回的结�?
		throw new RuntimeException(" err");
	}

	public long httpfileSize(String url) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				// ExUtil.throwExV4(string, dbginfo);
				String url2 = "url:" + url;
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == "
						+ httpResponse.getStatusLine().getStatusCode() + " " + url2);

			}
			return httpResponse.getEntity().getContentLength(); // 方法1
		} catch (IOException e) {
			ExUtil.throwExV2(e);
		}
		return 0;

	}

	public String httpget_wzbody(String url, String bodystr) {
		CloseableHttpClient httpCilent = HttpClients.createDefault();// Creates
																		// CloseableHttpClient
																		// instance
																		// with
																		// default
																		// configuration.
		HttpGet httpGet = new HttpGet(url);

		// 构建消息实体
		StringEntity entity = new StringEntity(bodystr, Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		// 发�?�Json格式的数据请�?
		entity.setContentType("application/json");

		((HttpResponse) httpGet).setEntity(entity);

		// httpCilent.execute(httpGet);
		String srtResult = "";

		HttpResponse httpResponse;
		try {
			httpResponse = httpCilent.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				srtResult = EntityUtils.toString(httpResponse.getEntity());

				return srtResult;
			} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 400");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				throw new RuntimeException("httpResponse.getStatusLine().getStatusCode() == 500");
			}

		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} // 获得返回的结�?
		throw new RuntimeException(" err");
	}

	/**
	 * 保存消息发�?�记�?
	 * 
	 * @param restUrl
	 * @param homessageMap
	 * @return 如果保存成功，返回对象的json格式内容；保存失败，返回空字符串
	 */
	public static String postEntiry2RestEasy(String restUrl, Map<String, Object> homessageMap) {

		if (restUrl == null || homessageMap == null || homessageMap.isEmpty()) {
			throw new IllegalArgumentException("invalid parameter to save HomessageRecord ! ");
		}

		StringBuilder outputBuider = new StringBuilder();

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(restUrl);

			JSONObject paramsEntity = new JSONObject(homessageMap);
			StringEntity input = new StringEntity(paramsEntity.toString(), "UTF-8");
			input.setContentType("application/json;");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() == 201) {
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
				String outputLine = null;

				while ((outputLine = br.readLine()) != null) {
					outputBuider.append(outputLine);
				}
				httpClient.getConnectionManager().shutdown();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputBuider.toString();
	}

	public static void main(String[] args) {
//		Map<String, Object> recordParams = new HashMap<String, Object>();
//		// key参�?�HoMessageRecord参数�?
//		recordParams.put("receiverName", "雍正�?");
//		recordParams.put("description", "雍正爷很勤劳");
//
//		String local = "http://localhost:8080/crmweb/cloud/hoMessageRecord";
//		String remote = "http://120.25.59.85:10500/crmweb/cloud/hoMessageRecord";
//		String rtnObj = HttpClientUtilV2Saa.postEntiry2RestEasy(remote, recordParams);
//		System.out.println(rtnObj);
	}

//	public static HttpClientUtilV2Saa newx() {
//		return new HttpClientUtilV2Saa();
//
//	}
}
