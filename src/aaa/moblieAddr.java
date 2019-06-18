package aaa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

 

public class moblieAddr {

	public static void main(String[] args) {
		Map m=Maps.newLinkedHashMap();
		m.put("name", "ati");m.put("mob", "159");
		
		List li=Lists.newArrayList();
		li.add(m);
		li.add(new HashMap (){{
			//put("姓名", "ati2");	put("mob", "200000");
		}});
		System.out.println(li);

	}

}
