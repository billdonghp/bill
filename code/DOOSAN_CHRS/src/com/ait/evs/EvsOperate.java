/*
 * 创建日期 2005-7-5
 * 
 * Company: AIT
 * 
 * @author QING
 * @version 1.0
 */
package com.ait.evs;

import java.io.Serializable;

/**
 * @author AIT Administrator
 */
public class EvsOperate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return 返回 evOperateId。
	 */
	public EvsOperate(String evOperateId, String evOperateName) {
		this.evOperateId = evOperateId;
		this.evOperateName = evOperateName;
	}

	public String getEvOperateId() {
		return evOperateId;
	}

	/**
	 * @param evOperateId
	 *            要设置的 evOperateId。
	 */
	public void setEvOperateId(String evOperateId) {
		this.evOperateId = evOperateId;
	}

	/**
	 * @return 返回 evOperateName。
	 */
	public String getEvOperateName() {
		return evOperateName;
	}

	/**
	 * @param evOperateName
	 *            要设置的 evOperateName。
	 */
	public void setEvOperateName(String evOperateName) {
		this.evOperateName = evOperateName;
	}

	private String evOperateId;

	private String evOperateName;

}