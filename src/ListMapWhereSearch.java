import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.filters.FixCrLfFilter.AddAsisRemove;

import com.beust.jcommander.internal.Lists;

import bsh.This;
import javassist.expr.NewArray;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class ListMapWhereSearch {
	
	public static void main(String[] args) throws OgnlException {
		List<Map> list=new ArrayList(){{
			add(new LinkedHashMap() {{
				put("age",12);put("name","jcc");
			}});
			add(new LinkedHashMap() {{
				put("age",15);put("name","aaa");
			}});
			add(new LinkedHashMap() {{
				put("age",18);put("name","aaa");
			}});
		 
		}};
		
		
		 OgnlContext context = new OgnlContext();
		 
		 
		  /*OGNL 过滤集合
	         * 表达式：collection.{? expression}*/
		 
		 
		 //first 过滤
	//	 System.out.println(  Ognl.getValue("[0]", list));   //{age=12, name=jcc}
		 
//		 System.out.println(  Ognl.getValue("[1].age", list));  // ok  ret 15

		 //查找所有遍历
		 //  System.out.println(  Ognl.getValue("#this", list));    //[{age=12, name=jcc}, {age=15, name=aaa}]
	//	 System.out.println(  Ognl.getValue("#root", list));   //ok as same to #this
 
		 
		 //条件where 过滤
		 //Object filteredCollection =	 Ognl.getValue("#root.{? #this.age > 13}", list);  // [{age=15, name=aaa}]
	// 		 System.out.println(filteredCollection);
		 
		 
		 //条件组合过滤
		 System.out.println( Ognl.getValue("#root.{?   #this.age!=15}", list));	 
		 
		  /*OGNL 过滤集合
         * 表达式：collection.{? expression}*/
     //   Object filteredCollection = Ognl.getValue("students.{? #this.length() > 6}", context, classroom);
   //     System.out.println("the student's length is longger than 2 is : " + filteredCollection);

        /**
         * OGNL 投影集合
         * 表达式：collection.{expression}
         * 与数据库投影单列类似
         * Ognl.getValue("collection.{<param>}", context, root);
         * */
//--------------------- 
//作者：杨帆最行 
//来源：CSDN 
//原文：https://blog.csdn.net/u013130967/article/details/51736973 
//版权声明：本文为博主原创文章，转载请附上博文链接！
	}

}
