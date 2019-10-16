package com.ait.evs.evs0126.bean;

public class EntEvsCode {
  public EntEvsCode() {
  }
  public int codeNo = 0;
  public String basicCode;
  public String basicName;
  public String parentCode;
  public String createDate;
  public String creatorID;
  public String modifyDate;
  public String modifierID;
  public int activity = 0;
  public int orderNo = 0;   
  public int depth = 0;
  public String codeEnName="";
  public String cpnyId;
  public String cpnyname;
  private String evsflag;
  public String descriptio;
  public String status;
  
  
  public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getDescriptio() {
	return descriptio;
}

public void setDescriptio(String descriptio) {
	this.descriptio = descriptio;
}

public String getCpnyname() {
	return cpnyname;
}

public void setCpnyname(String cpnyname) {
	this.cpnyname = cpnyname;
}

public String getEvsflag() {
	return evsflag;
}

public void setEvsflag(String evsflag) {
	this.evsflag = evsflag;
}

public int getCodeNo() {
    return this.codeNo;
  }

  public void setCodeNo(int codeNo) {
    this.codeNo =codeNo;
  }

  public String getBasicCode() {
    return this.editNull(basicCode);
  }

  public void setBasicCode(String basicCode) {
    this.basicCode = basicCode;
  }

  public void setBasicName(String basicName){
     this.basicName=basicName;
   }

  public String getBasicName() {
    return this.editNull(basicName);
  }

  public  String getParentCode(){
    return this.editNull(parentCode);
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

   public String getCreateDate() {
    return this.editNull(createDate);
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getCreatorID() {
    return this.editNull(creatorID);
  }

  public void setCreatorID(String creatorID) {
    this.creatorID = creatorID;
  }

  public String getModifyDate() {
    return this.editNull(modifyDate);
  }

  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String getModifierID() {
    return this.editNull(modifierID);
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

  public String editNull(String s) {
    if (s == null || s.equals("")) {
      return "";
    }
    else {
      return s;
    }
  }


public String getCodeEnName() {
	return codeEnName;
}


public void setCodeEnName(String codeEnName) {
	this.codeEnName = codeEnName;
}

public String getCpnyId() {
	return cpnyId;
}

public void setCpnyId(String cpnyId) {
	this.cpnyId = cpnyId;
}


}
