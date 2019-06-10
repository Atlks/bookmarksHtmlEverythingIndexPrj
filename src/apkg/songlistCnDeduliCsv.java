package apkg;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;

public class songlistCnDeduliCsv {

	public static void main(String[] args) throws IOException {
		Set<String> st=Sets.newConcurrentHashSet();
		File file = new File("d:\\MusicMetaJsonList.txt");
		JSONArray  ja=JSONArray.parseArray(FileUtils.readFileToString(file,"gbk"));
		for (Object object : ja) {
			JSONObject jo=(JSONObject) object;
		//	String string = jo.getString("SongName")+","+jo.getString("Artist")+","+jo.getString("Album");
			String string=jo.getString("Album");
			st.add(string);
			
		}
	 System.out.println(st.size());
		
		for (String s : st) {
			if(s.trim().length()>0)
			{
				char c=s.charAt(0);
				int i=c;
				if(c>255 || c<0) //online hezi
				System.out.println(s);
			}
			
		}

	}

}
