package agenepkg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class geneCls {

	public static void main(String[] args) {
		
		List<String> li1=Lists.newArrayList();
		List<Integer> li2=Lists.newArrayList();
		List<?> li3=Lists.newArrayList();
	//	li3.add(1);
		li3.add("2");
	//	li3.addAll(li1);
	//	li3.addAll(li1);
		
		
	 	Map<?,Object>  m=new HashMap<String,Object> ();
	 	

		Integer meth1 = geneCls. <Integer>meth1();
		System.out.println(meth1);
	}
	
	
	public static <类型参数> void print2(类型参数 param2 ) {

		 System.out.println(param2);
	}
	public static <t> void print(t param2 ) {

		 System.out.println(param2);
	}

	public static <t> t meth1( ) {

		{
			t t1 = (t) "aa";
		
			return t1;
		}

	}
	// public static t meth1<t>(){
	// return "";
	// }

}
