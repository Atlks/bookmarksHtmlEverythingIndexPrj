package com.attilax.img;

public class HsvRange {

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
	public int getvMin() {
		return vMin;
	}
	public void setvMin(int vMin) {
		this.vMin = vMin;
	}
	public int getvMax() {
		return vMax;
	}
	public void setvMax(int vMax) {
		this.vMax = vMax;
	}
	public int hMin;
	public int hMax;
	public double sMin;
	public double sMax;
	public double vMin;
	public double vMax;
	public boolean contain(HSV hs) {
		 
		return  hs.h>this.hMin && hs.h<this.hMax && hs.s>this.sMin && hs.s<this.sMax && hs.v>this.vMin && hs.v<this.vMax;
	}

}
