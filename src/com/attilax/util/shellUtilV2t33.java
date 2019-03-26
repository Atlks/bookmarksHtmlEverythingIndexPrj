package com.attilax.util;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.attilax.data.csv.csvService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class shellUtilV2t33 {

	public static List<Map> parse(String result) {
		List<Map> tab =shellUtilV2t33.toTableNoHeadMode_ByMultiSpace(result);
				//csvService.toTableByTab(result);;
		return 	tab;
	}
	
	
	public static List<Map> toTableNoHeadMode_ByMultiSpace(String csv) {
		List<Map> li = Lists.newArrayList();
		String[] rows = csv.split("\n");
		int idx = 0;
		for (String row : rows) {
			row=row.trim();
			if(row.length()==0)
				continue;
			Map m = Maps.newLinkedHashMap();
			String[] cols = row.split(" ");
			
			List<String> cols_li=getcols_li(cols);
			for (int j = 0; j < cols_li.size(); j++) {

				m.put("index" + String.valueOf(j), cols_li.get(j));

			}
			li.add(m);

		}
		return li;
	}
	
	
	private static List<String> getcols_li(String[] cols) {
 
		List<String> stringA = Arrays.asList(cols);
		List<String>  li=	Listutil.delEmptyElement(stringA);
		return li;
	}

	public static int getPid(List<Map> tab, String kewword, int pidIndex) {
		Map map = grepBykewword(tab, kewword);
		return Integer.parseInt(map.get("index"+pidIndex).toString().trim());
	}

	private static Map grepBykewword(List<Map> tab, String kewword) {
		for (Map map : tab) {
			String string = JSON.toJSONString(map);
			if (string.contains(kewword))
				return map;
		}
		return null;
	}

//	public static List<Map> parse_netstatNfindstr(String result) {
//		List<Map> tab =toTableNoHeadMode_ByMultiSpace(result);
//		tab.remove(0);
//		
//		return csvService.toTableBySpace_firstlineTitle(tab);
//	}

}
