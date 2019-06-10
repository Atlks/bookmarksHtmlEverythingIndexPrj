package agenepkg;

import java.util.ArrayList;

//泛型类
public class geneClsKstm<kkk> {
	
	kkk  param1;
	public void printInt(Integer pa)
	{
		System.out.println(pa);
	}
	//泛型模式来使用,好处 方便ide检查错误
	public static void main(String[] args) {
		geneClsKstm<Integer>  class1= new geneClsKstm<Integer>();
	//	class1.printInt(33);
		class1.param1=111;
	//	class1.param1="aaa";
	}

}
