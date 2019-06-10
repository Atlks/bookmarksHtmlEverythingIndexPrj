package agenepkg;

import java.util.ArrayList;

public class geneClsa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//泛型类
		
		
		//非泛型模式
		  ArrayList   list=new ArrayList<>(); 
		  list.add("");
		  
		  //泛型模式来使用,好处 方便ide检查错误
		  ArrayList<Integer>   list2=new ArrayList<>(); 
		  list2.add(1);  
		  //list2.add("");

	}

}
