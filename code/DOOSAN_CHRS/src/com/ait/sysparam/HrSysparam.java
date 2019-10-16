package com.ait.sysparam;

public class HrSysparam extends Sysparam {

	private final static String default_paramName = "人事参数设置";

	private final static String default_description = "人事参数设置";

	public void initialized() {
		this.setParamName(default_paramName);
		this.setDescription(default_description);
	}

	/**
	 * 试用期月数
	 */
	private int probationalMonths = 3;

	public int getProbationalMonths() {
		return probationalMonths;
	}

	public void setProbationalMonths(int probationalMonths) {
		this.probationalMonths = probationalMonths;
	}
}
