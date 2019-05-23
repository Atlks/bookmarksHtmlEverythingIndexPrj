/**
 * 
 */
package com.attilax.img.other;

/**
 * @author attilax
 *2017年1月21日 下午10:58:32
 */
public class CantFindMatch extends Exception {

	/**
	 * @param string
	 */
	public CantFindMatch(String string) {
		super(string);
	}
	
	public CantFindMatch(String string,Throwable t) {
		super(string, t);
	}

}
