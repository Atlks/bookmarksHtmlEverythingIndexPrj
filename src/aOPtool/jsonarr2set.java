package aOPtool;

import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Sets;

public class jsonarr2set {
	
	
	@SuppressWarnings("all")
	public static void main(String[] args) {
		
	//	com.alibaba.fastjson.JSONArray 
		Set set=Sets.newLinkedHashSet();
		set.add("aa");set.add("bb");
		String string=JSON.toJSONString(set, true);
		Object parse = JSON.parse(string);
		Set set2 = array2set(parse);
		System.out.println(set2);
	}

	private static Set array2set(Object parse) {
		JSONArray v=(JSONArray) parse;
		
		
		Set set2=Sets.newLinkedHashSet();
		for (Object object : v) {
		//	JSONObject jo=(JSONObject) object;
			set2.add(object);
		}
		return set2;
	}

}
