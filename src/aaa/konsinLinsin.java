package aaa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.attilax.time.sysncTimeX;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class konsinLinsin {
	
	public static void main(String[] args) {
		
		int centerX=10;
		int layers = 9;
		int layer=layers;
	//	System.out.println("*");
		for (int i = 0; i < layers; i++) {
			 	
			spaceLeft(centerX-i);
			System.out.print("*");
			countSpaceNum(i);
			System.out.println("*");
		}
		System.out.println("*");
		for (int i = 0; i < layers; i++) {
		 	
			spaceLeft(i);
			System.out.print("*");
			countSpaceNum(layers-i);
			System.out.println("*");
		}
	//	System.out.println("*");
	}

	private static void countSpaceNum( int cur_layer) {
		List li=Lists.newArrayList();
		for (int i = 0; i < cur_layer*2; i++)
		{
			 li.add(" ");
		     	
		}
		System.out.print(Joiner.on("").join(li));
		
	}

	private static char[] spaceLeft(int max) {
		
		List li=Lists.newArrayList();
		for (int i = 0; i < max; i++)
		{
			 li.add(" ");
		     	
		}
		System.out.print(Joiner.on("").join(li));
		return null;
	 
	}

}
