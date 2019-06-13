package aaaHttpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
 

public class httpclientDemo {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		 
 
      String url = "http://localhost:8088/list.json";
	String html = EntityUtils.toString(HttpClients.createDefault().execute(new HttpGet(url)).getEntity());
	System.out.println(html);   
         // ��ӡ��Ӧ״̬      
        

	}

}
