package apkg;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.attilax.net.http.HttpClientUtilV3t55;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import data.html.html2txtConverter;

public class SearchSongYear {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\searchByekae_rzt.txt");
		FileUtils.write(file,"[\r\n",true);
		boolean contstat=false;
		
		
		String f="d:\\ati can song.txt";
		List<String> li=FileUtils.readLines(new File(f),"gbk");
		for (String line : li) {
			line=line.trim();//song name
			
			
			Map m = searchByekae(line);
			// Map m=search(song);
			System.out.println(JSON.toJSONString(m, true));
			if(contstat)
				FileUtils.write(file,"\r\n,\r\n",true);
			FileUtils.write(file, JSON.toJSONString(m, true), true);
			if(!contstat)
				contstat=true;
		}
		
		
	 
		
		FileUtils.write(file,"\r\n]\r\n",true);
		// honchen cinge
	//	test();

	}

	private static void test() throws IOException, Exception {
		String songs = "红尘情歌,时间煮雨,未成年本兮";
		// songs = "时间煮雨";
		String[] sa = songs.split(",");
		File file = new File("D:\\searchByekae_rzt.txt");
		FileUtils.write(file,"[\r\n",true);
		boolean contstat=false;
		for (String song : sa) {
			Map m = searchByekae(song);
			// Map m=search(song);
			System.out.println(JSON.toJSONString(m, true));
			if(contstat)
				FileUtils.write(file,"\r\n,\r\n",true);
			FileUtils.write(file, JSON.toJSONString(m, true), true);
			if(!contstat)
				contstat=true;
			
			
		}
		
		FileUtils.write(file,"\r\n]\r\n",true);
	}

	private static Map searchByekae(String song) throws Exception {
		System.out.println(song);
		String url = "http://baike.baidu.com/item/" + URLEncoder.encode(song);
		String html2 = infoHtml(url);
		FileUtils.write(new File("d:\\recy\\htmlbyekae_firstitem_.html"), html2);
		Map info = getInfo(html2);

		return info;
	}

	private static String infoHtml(String url) throws Exception, IOException {

		String html = HttpClientUtilV3t55.get(url, "utf8");
		FileUtils.write(new File("d:\\recy\\htmlbyekae.html"), html);
		try {
			Document doc = Jsoup.parse(html);
			Element polysemantList = doc.select("ul.polysemantList-wrapper").get(0);
			Element item = polysemantList.select("li a").get(0);
			String link = item.attr("href");
			String url2 = "http://baike.baidu.com" + link;
			String html2 = HttpClientUtilV3t55.get(url2, "utf8");
			return html2;
		} catch (IndexOutOfBoundsException e) {
			return html;
		}

	}

	private static Map getInfo(String html) {
		Document doc = Jsoup.parse(html);
		Elements basicInfos = doc.select("dl.basicInfo-block");
		Map m = Maps.newLinkedHashMap();
		for (Element e : basicInfos) {
			Elements dt = e.select("dt");
			Elements dd = e.select("dd");
			for (int n = 0; n < dt.size(); n++) {
				m.put(dt.get(n).text(), dd.get(n).text());
			}
		}
		return m;
	}

	private static Map search(String song) throws Exception {
		// List<Map> li=Lists.newArrayList();
		Map m = Maps.newLinkedHashMap();
		search(song, new Consumer<List>() {

			@Override
			public void accept(List t) {
				m.put(song, t);

			}
		});
		return m;
	}

	private static void search(String song, Consumer<List> consumer) throws IOException, ClientProtocolException {

		String url = "http://www.baidu.com/s?wd=" + URLEncoder.encode(song + "  百度百科");
		// 执行get请求.
		CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
		// 获取响应实体
		String html = EntityUtils.toString(response.getEntity());
		FileUtils.write(new File("d:\\recy\\htmltmp.html"), html);
		// System.out.println(html);
		System.out.println("********************************down is doc.select\r\n\r\n\r\n\r\n");
		Document doc = Jsoup.parse(html);
		Element content_left = doc.getElementById("content_left");
		List<Map> li = Lists.newArrayList();
		Elements masthead = content_left.select("div.c-row");
		for (Element e : masthead) {
			Map m = Maps.newLinkedHashMap();
			String text = e.text();
			m.put("kw", song);
			m.put("txt", text);

			// System.out.println("\r\n" + text);
			Set<String> tel = com.attilax.text.RegexUtil.getYears(text, 1972, 2020);
			m.put("years", tel);
			li.add(m);

			// System.out.println(JSON.toJSONString(tel, true));
		}
		consumer.accept(li);
	}

}
