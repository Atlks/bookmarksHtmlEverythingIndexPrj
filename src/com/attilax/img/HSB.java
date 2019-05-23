package com.attilax.img;

import com.attilax.json.AtiJson;

public class HSB extends HSV {

	public float h;
	public float s;
	public float v;
	public int y;
	public int x;
	private float s255;
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

	public HSB(float h, float s, float v) {
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

	public HSB() {
		// TODO Auto-generated constructor stub
	}

	public String toString()
	{
		return AtiJson.toJson(this);
	}

	public HSB setHsv255mode(int h, int s, int v) {
		 this.h=h;
		 this.s=s/255;
		 this.v=v/255;
		return this;
	}

	public HSB setHsv100mode(int h1, int s1, int v1) {
		 this.h=h1;
		 this.s=(float)s1/(float)100;
		 this.v=(float)v1/(float)100;
		return this;
	}

//	public boolean contain(HSV hs) {
//		 
//		return  hs.h>this.h;
//	}

}
