package com.attilax.img.other;

import java.awt.Point;


@FunctionalInterface 
public interface ProcessPointColor {

	
    void apply(Point p) throws CurPixArrivdBoderEx ;
}
