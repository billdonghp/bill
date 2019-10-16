package com.ait.sy.sy0102.bean;

import org.apache.commons.lang.builder.ToStringBuilder;


public class Ent {
  public Ent() {
  }
  public int deptNo = 0;
  public String deptID;
  public String deptName;
  public String companyID;
  public String createdDate;
  public String parentDeptNo;
  public int deptLevel = 0;
  public String createDate;
  public String creatorID;
  public String modifyDate;
  public String modifierID;
  public String empID;
  public String deptEnName="";
  public String ChineseName;
  public String ChiesePinYin;
  public String kor_name ;
  public String en_name; 
  public int activity = 0;
  public int orderNo = 0;
  public String endEddate;
  public String checked;
  private String deptcode;
  private String parentDeptName;
  

  public int getDeptNo() {
    return this.deptNo;
  }

  public void setDeptNo(int deptNo) {
    this.deptNo = deptNo;
  }

  public String getDeptID() {
    return this.editNull(deptID);
  }

  public void setDeptID(String deptID) {
    this.deptID = deptID;
  }

  public String getChecked() {
    return this.editNull(checked);
  }

  public void setChecked(String checked) {
  this.checked = checked;
}

public String getDeptName() {
  return this.editNull(deptName);
}


  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }


  public String getCompanyID() {
    return this.editNull(companyID);
  }

  public void setCompanyID(String companyID) {
    this.companyID = companyID;
  }

  public String getCreatedDate() {
    return this.editNull(createdDate);
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getParentDeptNo() {
    return this.editNull(parentDeptNo);
  }

  public void setParentDeptNo(String parentDeptNo) {
    this.parentDeptNo = parentDeptNo;
  }

  public int getDeptLevel() {
    return this.deptLevel;
  }

  public void setDeptLevel(int deptLevel) {
    this.deptLevel = deptLevel;
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

  public String getEmpID() {
     return this.editNull(empID);
   }

   public void setEmpID(String empID) {
     this.empID = empID;
   }

   public String getChineseName() {
      return this.editNull(ChineseName);
    }

    public void setChineseName(String ChineseName) {
      this.ChineseName = ChineseName;
    }

    public String getChiesePinYin() {
       return this.editNull(ChiesePinYin);
     }

     public void setChiesePinYin(String ChiesePinYin) {
       this.ChiesePinYin = ChiesePinYin;
     }

     public String getEndEddate() {
        return this.editNull(endEddate);
      }

      public void setEndEddate(String endEddate) {
        this.endEddate = endEddate;
      }



  public String editNull(String s) {
    if (s == null || s.equals("")) {
      return "";
    }
    else {
      return s;
    }
  }
  public String getDeptcode() {
    return deptcode;
  }
  public void setDeptcode(String deptcode) {
    this.deptcode = this.editNull(deptcode);
  }
  public String getParentDeptName() {
    return parentDeptName;
  }
  public void setParentDeptName(String parentDeptName) {
    this.parentDeptName = parentDeptName;
  }

/**
 * @see java.lang.Object#toString()
 */
public String toString() {
    return new ToStringBuilder(this).append("chineseName",
    	this.getChineseName()).append("orderNo", this.orderNo).append(
    	"endEddate", this.endEddate).append("modifyDate",
    	this.modifyDate).append("createdDate", this.createdDate)
    	.append("deptName", this.deptName).append("creatorID",
    		this.creatorID).append("modifierID", this.modifierID)
    	.append("deptLevel", this.deptLevel).append("deptNo",
    		this.deptNo).append("checked", this.checked).append(
    		"deptID", this.deptID).append("empID", this.empID)
    	.append("activity", this.activity).append("companyID",
    		this.companyID).append("deptcode", this.deptcode)
    	.append("parentDeptNo", this.parentDeptNo).append(
    		"chiesePinYin", this.getChiesePinYin()).append(
    		"parentDeptName", this.parentDeptName).append(
    		"createDate", this.createDate).toString();
}


public String getDeptEnName() {
	return deptEnName;
}


public void setDeptEnName(String deptEnName) {
	this.deptEnName = deptEnName;
}

public String getEn_name() {
	return en_name;
}

public void setEn_name(String en_name) {
	this.en_name = en_name;
}

public String getKor_name() {
	return kor_name;
}

public void setKor_name(String kor_name) {
	this.kor_name = kor_name;
}
  
}
