/**
 * @date 2006-6-15
 */
package com.ait.jdbc.core;


/**
 * sql 参数类型
 * @see org.springframework.jdbc.core.SqlParameter
 */
public class SqlParameter {
	
	private ParameterType sqlType;
	
	private Object inValue;

	public SqlParameter() {
	}

	public SqlParameter(ParameterType sqlType, Object inValue) {
		this.sqlType = sqlType;
		this.inValue = inValue;
	}

	public SqlParameter(int inValue){
		this.sqlType = ParameterType.INTEGER;
		this.inValue = new Integer(inValue);
	}

	public Object getInValue() {
		return inValue;
	}

	public void setInValue(Object inValue) {
		this.inValue = inValue;
	}

	public ParameterType getSqlType() {
		return sqlType;
	}

	public void setSqlType(ParameterType sqlType) {
		this.sqlType = sqlType;
	}
	
	public String toString(){
		return "[" + sqlType + "," + inValue + "]";
	}

}
