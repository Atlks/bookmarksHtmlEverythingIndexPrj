package aOPtool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.attilax.net.URIparser;
import com.attilax.util.FileCacheManager;
import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class dirSizeLookerDutool {

	public static void main(String[] args) throws Exception {

		String jumpUrl = FileUtils.readFileToString(new File("H:\\0db\\pre11.txt"));
		URIparser Jmpr_urIparser = new URIparser(jumpUrl);

		// 加入SCP实现远程传输文件
		Connection con = new Connection(Jmpr_urIparser.getHost(), Jmpr_urIparser.getPort());

		// 连接

		con.connect();

		// 登陆远程服务器的用户名密码
		boolean isAuthed;

		isAuthed = con.authenticateWithPassword(Jmpr_urIparser.getUser(), Jmpr_urIparser.getPwd());
		String cmd = "du -h / --max-depth=1";
		List<Map<String, Object>> subList = cmdDuRootWithRztCache(con, cmd);		
		String jsonString = JSON.toJSONString(subList, true);
		
		System.out.println(jsonString);

	}

	private static List<Map<String, Object>> cmdDuRootWithRztCache(Connection con, String cmd) throws IOException {
		FileCacheManager fcm=new FileCacheManager("g:/0db/cmdcache");
	if(	fcm.exist(filenameutilsT22.filenameEncodeByCmd(cmd))) {
		return fcm.get(key);
	}
		Session session = con.openSession();
	
		session.execCommand(cmd);

		List<Map<String, Object>> list = Lists.newArrayList();
		InputStream is = new StreamGobbler(session.getStdout());// 获得标准输出流
		BufferedReader brs = new BufferedReader(new InputStreamReader(is));
		for (String line = brs.readLine(); line != null; line = brs.readLine()) {
			// result.add(line);
			System.out.println(line);
			Map mp = Maps.newLinkedHashMap();
			String[] rStrings = line.split("\t");
			String size = rStrings[0];
			if (size.trim().equals("0"))
					continue;
			if (size.trim().endsWith("K"))
				continue;
			if (size.trim().endsWith("M")) {
				mp.put("sizeM", size.trim().substring(0, size.length() - 1));
				float sieeM = MapUtils.getFloatValue(mp, "sizeM");
				mp.put("sizeG", sieeM / 1000);
			} else if (size.trim().endsWith("G")) {
				mp.put("sizeG", size.trim().substring(0, size.length() - 1));
				float sizeG = MapUtils.getFloatValue(mp, "sizeG");
				mp.put("sizeM", sizeG * 1000);

			}
			mp.put("f", rStrings[1]);
			list.add(mp);
			System.out.println(mp);
		//	System.out.println(line);
		}

		list.sort((m1, m2) -> MapUtils.getFloat(m2, "sizeM").compareTo(MapUtils.getFloat(m1, "sizeM")));

		List<Map<String, Object>> subList = list.subList(0, 7);
		String jsonString = JSON.toJSONString(subList, true);
		return subList;
	}

}
