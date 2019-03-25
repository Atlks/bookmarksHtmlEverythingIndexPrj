package bookmarksHtmlEverythingIndexPrj;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.attilax.io.FilenameUtil;

public class bookmarkHtmlEveIndexGener {

	public static void main(String[] args) throws IOException {

		String htmlEncode = "utf8";
		Document doc = Jsoup.parse(new File("C:\\Users\\zhoufeiyue\\Documents\\bookmarks_2019_3_9.html"), htmlEncode,
				"http://baseuri.com/");
		String qqBrowerBookmarkEverythinIndexDir = "g:\\qqBrowerBookmarkEveryIndex";

		// Element tab_elmt=doc.getElementById(tableId);
String fString="https%3A%2F%2Fv%2Ehtml5%2Eqq%2Ecom%2Fnode%2Fplayer%3Fvid=34446145&ch";
		String pathname =fString+ "=001500&extinfo=bizId%3D5%26ch%3D003801%26iRunTime%3D25%26iTagId%3D156%26modid%3D17%26polid%3D1%26qbid%3D1844382d1f06c271%26sPageUrl%3D%26src%3D65%26type%3D15%26vid%3D34446145&topicid=156&sch=001502&i=1&sc_id=liJZyzC.plshldr";
	
	//	FileUtils.write(new File(pathname), "dd");
		
 
		Elements trs = doc.getElementsByTag("a");
		for (Element tr : trs) {
			try {
				String filename = tr.text();
				String filename_encode = FilenameUtil.fileNameEncodeUrlencMode(filename,qqBrowerBookmarkEverythinIndexDir);

				System.out.println(filename);
				FileUtils.write(new File(qqBrowerBookmarkEverythinIndexDir+"/"+filename_encode+".plshldr"), "dt");
			} catch (Exception e) {
				FileUtils.write(new File("bookmarkHtmlEveIndexGenerLogDir/log.txt"), JSON.toJSONString(e,true));
			}
			

		}
		//https%3A%2F%2Fv%2Ehtml5%2Eqq%2Ecom%2Fnode%2Fplayer%3Fvid=34446145&ch=001500&extinfo=bizId%3D5%26ch%3D003801%26iRunTime%3D25%26iTagId%3D156%26modid%3D17%26polid%3D1%26qbid%3D1844382d1f06c271%26sPageUrl%3D%26src%3D65%26type%3D15%26vid%3D34446145&topicid=156&sch=001502&i=1&sc_id=liJZyzC.plshldr
		System.out.println("--f");
	}

}
