package com.ait.utils;

import java.io.Serializable;

import com.ait.util.StringUtil;

public class MenuEnt implements Serializable {

	private int menuNo = 0;

	private String menuCode;

	private String menuIntro;

	private String menuEnIntro;
	
	private String menuKorIntro;

	private String menuParentCode;

	private String tableName;

	private String menuUrl;

	private String menuImage;

	private String createDate;

	private String creatorID;

	private String modifyDate;

	private String modifierID;

	private int insertr = 0;

	private int updater = 0;

	private int deleter = 0;

	private int activity = 0;

	private int orderNo = 0;

	private int depth = 0;

	public int getMenuNo() {
		return this.menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuCode() {
		return StringUtil.checkNull(this.menuCode);
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuImage() {
		return StringUtil.checkNull(this.menuImage);
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public String getMenuParentCode() {
		return StringUtil.checkNull(this.menuParentCode);
	}

	public void setMenuParentCode(String menuParentCode) {
		this.menuParentCode = menuParentCode;
	}

	public String getMenuIntro() {
		return StringUtil.checkNull(this.menuIntro);
	}

	public void setMenuIntro(String menuIntro) {
		this.menuIntro = menuIntro;
	}

	public String getTableName() {
		return StringUtil.checkNull(this.tableName);
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMenuUrl() {
		return StringUtil.checkNull(this.menuUrl);
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getCreateDate() {
		return StringUtil.checkNull(this.createDate);
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatorID() {
		return StringUtil.checkNull(this.creatorID);
	}

	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}

	public String getModifyDate() {
		return StringUtil.checkNull(this.modifyDate);
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifierID() {
		return StringUtil.checkNull(this.modifierID);
	}

	public void setModifierID(String modifierID) {
		this.modifierID = modifierID;
	}

	public int getActivity() {
		return this.activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public int getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getDeleter() {
		return deleter;
	}

	public void setDeleter(int deleter) {
		this.deleter = deleter;
	}

	public int getInsertr() {
		return insertr;
	}

	public void setInsertr(int insertr) {
		this.insertr = insertr;
	}

	public int getUpdater() {
		return updater;
	}

	public void setUpdater(int updater) {
		this.updater = updater;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {

		if (!(object instanceof MenuEnt)) {
			return false;
		}
		MenuEnt rhs = (MenuEnt) object;
		if (rhs.menuCode.equals(this.menuCode)) {
			return true;
		} else {
			return false;
		}

	}

	public String getMenuEnIntro() {
		return menuEnIntro;
	}

	public void setMenuEnIntro(String menuEnIntro) {
		this.menuEnIntro = menuEnIntro;
	}

	public String getMenuKorIntro() {
		return menuKorIntro;
	}

	public void setMenuKorIntro(String menuKorIntro) {
		this.menuKorIntro = menuKorIntro;
	}

}
