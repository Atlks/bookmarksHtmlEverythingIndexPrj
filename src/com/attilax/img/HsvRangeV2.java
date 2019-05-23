package com.attilax.img;

public class HsvRangeV2 {
	
	HSV low;
	HSV hi;
	
	public HsvRangeV2(HSV low, HSV hi) {
		 this.low=low;
		 this.hi=hi;
		 this.hMax=(int) hi.h;
		 this.hMin=(int) low.h;
		 this.sMax=hi.s;
		 this.sMin=low.s;
		 this.vMax=hi.v;
		 this.vMin=low.v;
	}

	public int gethMin() {
		return hMin;
	}
	public void sethMin(int hMin) {
		this.hMin = hMin;
	}
	public int gethMax() {
		return hMax;
	}
	public void sethMax(int hMax) {
		this.hMax = hMax;
	}
	public double getsMin() {
		return sMin;
	}
	public void setsMin(double sMin) {
		this.sMin = sMin;
	}
	public double getsMax() {
		return sMax;
	}
	public void setsMax(double sMax) {
		this.sMax = sMax;
	}
	 
	public void setvMin(int vMin) {
		this.vMin = vMin;
	}
	 
	public double getvMin() {
		return vMin;
	}
	public void setvMin(double vMin) {
		this.vMin = vMin;
	}
	public double getvMax() {
		return vMax;
	}
	public void setvMax(double vMax) {
		this.vMax = vMax;
	}
	public void setvMax(int vMax) {
		this.vMax = vMax;
	}
	public double hMin;
	public double hMax;
	public double sMin;
	public double sMax;
	public double vMin;
	public double vMax;
	public boolean contain(HSV hs) {
		 
		return  hs.h>this.hMin && hs.h<this.hMax && hs.s>this.sMin && hs.s<this.sMax && hs.v>this.vMin && hs.v<this.vMax;
	}

}
