package cn.multiseafoods.crm.exception;

import cn.multiseafoods.crm.enums.ResultEnum;

public class EmployeeException extends RuntimeException {
    private Integer code;

    public EmployeeException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
