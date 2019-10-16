/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-8-18
 */
package com.ait.utils;

import java.io.Serializable;

/**
 * 
 * @version 
 */
public class IntPair   implements Serializable{

	private static final long serialVersionUID = 1L;

	private int min;
	
	private int max;

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

}
