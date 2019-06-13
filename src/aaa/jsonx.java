package aaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class jsonx {

	public static void main(String[] args) {

         List  list=new ArrayList<>();
         Map m=new HashMap<>();
         m.put("age", 17);
         m.put("li", new ArrayList<>());
         list.add(m);
         
         
         Map m2=new HashMap<>();
         m2.put("age", 19);
         list.add(m2);
         
         
         List list2=new ArrayList(){{
        	 add(new HashMap(){{
        		 put("age", 11);
        	 }});
        	 add(new HashMap(){{
        		 put("age", 12);
        	 }});
         }};
         
         
         System.out.println( JSON.toJSONString(list2, true));
         
         

	}

}
