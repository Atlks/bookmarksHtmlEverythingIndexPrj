package aOPtool;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.attilax.net.HttpServletRequestImp;
import com.attilax.net.URIparser;
import com.attilax.text.HeziUtil;
import com.attilax.util.ExUtilV2t33;
import com.attilax.util.ExceptionAti;
import com.beust.jcommander.internal.Lists;

public class clrusreTest {
	

	@org.junit.jupiter.api.Test
	  void     getTest()   {
		try {
			HttpServletRequestImp imp=new HttpServletRequestImp();
		//	String string = "查看：//18821766710:056060@唐唐云学堂; 删除://18821766710:056060@唐唐云学堂；查看：//18821766710:056060@唐唐云学堂";
			String string = "查看：//18821766710:056060@唐唐云学堂;  查看：//18821766710:05@唐唐云学堂";
			
			
			imp.setParameter("param",string);
			HttpServletResponseImp respImp=new HttpServletResponseImp();
			new clruser().api416(imp, respImp);

		} catch (Exception e) {
			ExUtilV2t33.throwExV2(e);
		}
		
	} 

	public static void main(String[] args) throws IOException, Exception {
	//	OpaqueUinput2ri
		System.err.println("errrrmsg");
//		
//	 
//		URI uriw=URI.create(mysqlConnUrl);
//		boolean isop=uriw.isOpaque(); //true
//		System.out.println(JSON.toJSONString(uriw));
//		//schema="jdbc"
// 	URIparser uri=URIparser.create("查看:18821766710@唐唐云学堂");
// 		System.out.println(uri.isOpaque());
		
		String string = "唐唐云学堂 删除 18821766710 056060";
		string = "唐唐云学堂 获取密码 18821766710";
		string = "查看：//18821766710:056060@唐唐云学堂";
	 	string = "查看：//18821766710:056060@唐唐云学堂; 删除://18821766710:056060@唐唐云学堂；查看：//18821766710:056060@唐唐云学堂";
	 	string = "查询:18821766710@唐唐云学堂； 删除:18821766710:660466@唐唐云学堂；查询:18821766710@唐唐云学堂； ";
//	  string = "查询密码：18821766710@唐唐云学堂  ";
//	 	string = "查询:18821766710@唐唐云学堂；";
//	 	string = " 删除:18821766710:660466@唐唐云学堂；";
	 	string=string.trim();
		string = HeziUtil.replaceHeziComma(string);
		//URI uri=new URI(string);
	//	URIparser urIparser=new URIparser(string);
	//	string = "唐唐云学堂 查看 18821766710;唐唐云学堂 删除 1882176671086 997672；唐唐云学堂 查看 18821766710 ";
 List list=  new clruser().	statmentMultiExec(string);

		System.out.println(JSON.toJSONString(list,true));

	}

	

}
