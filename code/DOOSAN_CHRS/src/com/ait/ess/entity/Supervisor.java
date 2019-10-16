package com.ait.ess.entity;

public class Supervisor {
    private String empId;

    private String chineseName;

    private String deptId;

    private String arSupervisorId;

    private String supervisorName;

    public Supervisor() {
        //constructor
    }

    //empId
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String a_empId) {
        empId = a_empId;
    }

    //chineseName
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String a_chineseName) {
        chineseName = a_chineseName;
    }

    //deptId
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String a_deptId) {
        deptId = a_deptId;
    }

    //arSupervisorId
    public String getArSupervisorId() {
        return arSupervisorId;
    }

    public void setArSupervisorId(String a_arSupervisorId) {
        arSupervisorId = a_arSupervisorId;
    }
    
    //supervisorName
    public void setSupervisorName(String a_supervisorName) {
        supervisorName = a_supervisorName;
    }
    
    public String getSupervisorName() {
        return supervisorName;
    }
}
