package agenepkg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.attilax.time.sysncTimeX;

public class regexpDemo {
   // regex  regular express  ,  正则表达式
	public static void main(String[] args) {

//		Pattern pattern = Pattern.compile("\\d+");
//		Matcher matcher = pattern.matcher("122aa");
//		//  字符串是否与正则表达式相匹配//
//		System.out.println(matcher.matches());
		
		//测试字符串是否匹配某个正则表达式模板，
		//检测年代  d=digit
		System.out.println(Pattern.compile("\\d{4}").matcher("2018").matches());
		
	   System.out.println("12344".matches("\\d{4}"));
		
		//Pattern类   看作模板
		// Pattern.compile方法，编译
		//Pattern.matcher  方法，，匹配
		
		//Matcher对象 ，匹配器对象，匹配结果对象
		//Matcher.matches()   测试字符串是否匹配某个模板

	}

}
