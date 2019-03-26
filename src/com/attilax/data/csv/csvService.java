package com.attilax.data.csv;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.json.CDL;
import org.json.JSONException;

import com.alibaba.fastjson.JSON;
import com.attilax.util.ExUtil;
import com.attilax.util.Listutil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class csvService {
	
	/**
	 * json 2 csv
	 * @return 返回csv格式的字符串
	 * @throws JSONException
	 */
	public String getCSVString(String jsonString) {
		// 将jsonArray转换成纯字符串（涵盖所有符号）

		// 利用字符串生成org.json.JSONArray,实现net.sf.json.jsonArray与org.json.JSONArray转换
		org.json.JSONArray orgjsonarray;
		try {
			orgjsonarray = new org.json.JSONArray(jsonString);

			// 利用org.json工具类生成CSV格式要求的String。

			String csv = CDL.toString(orgjsonarray);
			String rzt =new csvService().  toPrettyFmtCsv(csv);

			return rzt;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExUtil.throwExV2(e);
		}
		return jsonString;
	}
	
	/**
	 * normal csv 2 pretty fmt csv
	 * @param csv
	 * @return
	 */
	public String toPrettyFmtCsv(String csv) {
		List<Map> tab = toTable(csv);
		Map col_maxleng = col_maxleng(tab);
		List<Map> tab2 = padCol(tab, col_maxleng);
		String rzt = toJoinCsv(tab2);
		return rzt;
	}

	private String toJoinCsv(List<Map> tab) {
		List<String> tabS = Lists.newArrayList();
		for (Map map : tab) {
			List<String> row = Lists.newArrayList();
			for (Object entry : map.entrySet()) {
				Map.Entry me = (Entry) entry;
				row.add((String) me.getValue());
			}
			String row_comma_join = Joiner.on(",").join(row);
			tabS.add(row_comma_join);
		}
		return Joiner.on("\r\n").join(tabS);
	}

	/**
	 * if use tab sould be best??
	 * saovei yodyar bug ,,,yaosi jong  ????￥?      zo rend sh hanze ,shjy d hwa bn double .beir zo k cheo l .
	 * 
	 * @param tab
	 * @param col_maxleng
	 * @return
	 */
	private List<Map> padCol(List<Map> tab, Map col_maxleng) {

		for (Map map : tab) {
			for (Object entry : map.entrySet()) {
				Map.Entry me = (Entry) entry;
				int maxleng = (int) col_maxleng.get(me.getKey());
				String val = me.getValue().toString();
//				if (val.equals("系统管理员"))
//					System.out.println("dbg");
//				if (val.equals("BCE03A"))
//					System.out.println("dbg");
//				if (val.equals("VCF11"))
//					System.out.println("dbg");

				int getCnCharCount=getCnCharCount(val);
				if(getCnCharCount>0 )
				{
					int aciicount=val.length()-getCnCharCount;
					int getCnCharCount_asAciileng=asAciileng(getCnCharCount);
					int alllen=aciicount+getCnCharCount_asAciileng;
					int needpad=maxleng-alllen;
					needpad=needpad*3;
					int so_last_len=alllen+needpad;
					String val_padded = StringUtils.rightPad(val, so_last_len, " ");
					me.setValue(val_padded);
					
					
				}else
				{
					String val_padded = StringUtils.rightPad(val, maxleng, " ");
					me.setValue(val_padded);
				}

			}
		}
		return tab;
	}

	private Map col_maxleng(List<Map> tab) {

		Map map_colIndex_colmaxLen = Maps.newLinkedHashMap();
		for (Map map : tab) {
			for (Object entry : map.entrySet()) {
				Map.Entry me = (Entry) entry;
//				if (me.getValue().toString().equals("VCF11"))
//					System.out.println("dbg");

				Object colIndex = me.getKey();
				int curIndexleng = getCurIndexLen(map_colIndex_colmaxLen, colIndex);
				int thisIndexleng = getLengAsAciiiChar(me.getValue().toString());
				if (thisIndexleng > curIndexleng) {
					map_colIndex_colmaxLen.put(colIndex, thisIndexleng);
					if (colIndex.equals("index6")) {
						Map mdbg = Maps.newLinkedHashMap();
						mdbg.put("colidx", colIndex);
						mdbg.put("curIndexlengInKey_lenMap", curIndexleng);
						mdbg.put("thisIndexleng", thisIndexleng);
						mdbg.put("thiscolval", me.getValue());
					//	System.out.println(JSON.toJSONString(mdbg));
					}
				}

			}
		}
		return map_colIndex_colmaxLen;
	}

	private int getLengAsAciiiChar(String s) {
//		if(s.equals("系统管理员"))
//			System.out.println("dbg");
		int getCnCharCount=getCnCharCount(s);
		int aciicount=s.length()-getCnCharCount;
		int getCnCharCount_asAciileng=asAciileng(getCnCharCount);
		return  aciicount+getCnCharCount_asAciileng;
	}

	private int asAciileng(int getCnCharCount) {
		float len=(float)getCnCharCount*7f/5f;
		
		Double ceil = Math.ceil((double) len);
		return ceil.intValue() ;
	}

	public int getCnCharCount(String str) {
		int count = 0;
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			String len = Integer.toBinaryString(c[i]);

			if (len.length() > 8)
				count++;
		}
		return count;
	}

	private int getCurIndexLen(Map m, Object colIndex) {
//		if (colIndex.equals("index6")) {
//			System.out.println("dbg");
//		}
		Object colleng = m.get(colIndex);
		if (colleng == null)
			return 0;
		else

			return Integer.parseInt(colleng.toString());
	}

	private List<Map> toTable(String csv) {
		List<Map> li = Lists.newArrayList();
		String[] rows = csv.split("\n");
		int idx = 0;
		for (String row : rows) {

			Map m = Maps.newLinkedHashMap();
			String[] cols = row.split(",");
			for (int j = 0; j < cols.length; j++) {

				m.put("index" + String.valueOf(j), cols[j]);

			}
			li.add(m);

		}
		return li;
	}
	
	/**
	 * from csv 
	 * @param csv
	 * @return
	 */
	public static List<Map> toTableByTab(String csv) {
		List<Map> li = toTableNoHeadBySpace(csv);
		List<Map> li2 = toTableBySpace(li);
		return li2;
		 
	}
	
	// wize head
	private static List<Map> toTableBySpace(List<Map> li) {
		List<Map> li_rzt=Lists.newArrayList();
		Map headmap=Maps.newConcurrentMap();
		for (int j = 0; j < li.size(); j++) {
				if(j==0)  //head
				{
					headmap= li.get(0);
					continue;
				}
				Map curmap=li.get(j);
				Map curmap2cutmkey=Maps.newLinkedHashMap();
				for (Object entry : curmap.entrySet()) {
					Map.Entry me = (Entry) entry;
					 String autokey=(String) me.getKey();
					 String key=(String) headmap.get(autokey);
					 curmap2cutmkey.put(key, me.getValue());
				}
				
				li_rzt.add(curmap2cutmkey);
			 

		}
		return li_rzt;
	}
	private static Map getheadmap(Map map) {
		Map headmap=Maps.newConcurrentMap();
		
		return null;
	}
	public static List<Map> toTableBySpace_ParseHeader(String csv) {
	 
		List<Map> tableNoHeadBySpace = toTableNoHeadBySpace(csv);
		List<Map> li2 = toTableBySpace(tableNoHeadBySpace);
		return li2;
	 
	}
	
	
	public static List<Map> toTableBySpace(String csv) {
		// TODO Auto-generated method stub
		return toTableNoHeadBySpace(csv);
	}
	// use toTableBySpace
	@Deprecated  
	private static List<Map> toTableNoHeadBySpace(String csv) {
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
	//	String[] sa=stdout_2str_ByIoutil.split(" ");
		List<String> stringA = Arrays.asList(cols);
		List<String>  li=	Listutil.delEmptyElement(stringA);
		return li;
	}
	public static List<String> parse(String stdout_2str_ByIoutil) {
		String[] sa=stdout_2str_ByIoutil.split("\r\n");
		List<String> stringA = Arrays.asList(sa);
		
		//List<String>  li=	Listutil.delEmptyElement(stringA);
		return null;
	}


}
