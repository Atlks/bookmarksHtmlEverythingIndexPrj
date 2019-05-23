/**
 * 
 */
package com.attilax.img;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author attilax
 *2016年11月8日 下午6:56:14
 */
@FunctionalInterface
public interface zdepre_ImgTraver_byScanline {
     public   void trave(int x,int y);
//	 default  void compose() {
//	        Objects.requireNonNull(before);
//	        return (V v) -> apply(before.apply(v));
//	    }
}
