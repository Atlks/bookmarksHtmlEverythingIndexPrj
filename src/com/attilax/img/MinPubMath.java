package com.attilax.img;

public class MinPubMath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double m=11.87;
		double n=1.88;
//		if(m<n)
//		{
//		   int t=m;
//		   m=n;
//		  n=t;
//		}
		for(double i=n;i<=m*n;i=i+0.01)
		{
		    if(i%m==0 && i%n==0)
		         { System.out.println("最小公倍数为"+i);break;}
		}
	}

}
