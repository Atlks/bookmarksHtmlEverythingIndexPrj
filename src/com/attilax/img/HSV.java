package com.attilax.img;

import java.awt.Color;

import com.attilax.img.other.ColorUtil;
import com.attilax.json.AtiJson;

public class HSV {

	public float h;  //360du mode
	public float s;  //max  0-100
	public float v; //100 mode
	public int y;
	public int x;
	private float s255;    //range 0-255
	private float v255;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getS() {
		return s;
	}

	public void setS(float s) {
		this.s = s;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public HSV(float h, float s, float v) {
		this.h=h;
		this.s=s;
		this.v=v;
		this.s255=s*255;
		this.v255=v*255;
	}
	public float getS255() {
		return s255;
	}

	public float getV255() {
		return v255;
	}

	public HSV() {
		// TODO Auto-generated constructor stub
	}

	public HSV(int clr_int) {
	   Color c=new Color(clr_int);
	   HSV hsv= new HSV();
	   hsv.setHsv255mode(c.getRed(), c.getGreen(), c.getBlue());
	 //  this.h=hsv.h;
	//   this.s=hsv.s;
	//   return this;
	//   this.v=hsv.v;
	}

	public String toString()
	{
		return AtiJson.toJson(this);
	}

	public HSV setHsv255mode(int h_255mode, int s2, int v2) {
		 this.h=h_255mode;
		 this.s=(float)s2/(float)255;
		 this.v=(float)v2/(float)255;
		 
		 this.h=h_255mode;
		 this.s255=s2;
		 this.v255=v2;
		 
		return this;
	}

	public HSV setHsv100mode(int h1, int s1, int v1) {
		 this.h=h1;
		 this.s=(float)s1/(float)100;
		 this.v=(float)v1/(float)100;
		return this;
	}

	//ret 100mode hsv
	public static HSV getHsvByColorint(int clr_int) {
		   Color c=new Color(clr_int);
		
		   HSV hsv_100mode= ColorUtil.to100ModeHsv(clr_int);
		   hsv_100mode.s=   hsv_100mode.s*100;
		   hsv_100mode.v=   hsv_100mode.v*100;
				//   new HSV();
		//   hsv_100mode.setHsv255mode(c.getRed(), c.getGreen(), c.getBlue());
		   
		//   ColorUtil.to100ModeHsv(clr_int);
		return hsv_100mode;
	}

//	public boolean contain(HSV hs) {
//		 
//		return  hs.h>this.h;
//	}

}
