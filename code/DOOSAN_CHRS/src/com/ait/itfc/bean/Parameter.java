package com.ait.itfc.bean;

public class Parameter {

	private int sequense;

	private int type;

	private String formula;
	
	private String emColumn ;

	public Parameter(int sequense, int type, String formula, String emColumn) {
		this.sequense = sequense;
		this.type = type;
		this.formula = formula;
		this.emColumn = emColumn ; 
	}

	public int getSequense() {
		return sequense;
	}

	public void setSequense(int sequense) {
		this.sequense = sequense;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getEmColumn() {
		return emColumn;
	}

	public void setEmColumn(String emColumn) {
		this.emColumn = emColumn;
	}
}