/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-6-23
 */
package com.ait.sysparam;
/**
 * 系统参数基类
 * @version 1.0
 */
public abstract class Sysparam {
    private String paramName;
    private String description;
    
    public Sysparam(){
    	initialized();
    }
    
    public abstract void initialized();
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String toString(){
		return new StringBuffer().append("paramName = ").append(paramName)
		                         .append("; description = ").append(description).toString();
	}
	
}
