package com.ait.gm.bean;

public class GmBean {
	private String gm_id_pk;
	private String gm_eatery_type;
	private String from_date;
	private String to_date;
	private String CARD_ENDORIDATE;
	public String getCARD_ENDORIDATE() {
		return CARD_ENDORIDATE;
	}
	public void setCARD_ENDORIDATE(String card_endoridate) {
		CARD_ENDORIDATE = card_endoridate;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getGm_eatery_type() {
		return gm_eatery_type;
	}
	public void setGm_eatery_type(String gm_eatery_type) {
		this.gm_eatery_type = gm_eatery_type;
	}
	public String getGm_id_pk() {
		return gm_id_pk;
	}
	public void setGm_id_pk(String gm_id_pk) {
		this.gm_id_pk = gm_id_pk;
	}
}
