package cn.multiseafoods.crm.enums;

public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"你还在上小学！"),
    MIDDLE_SCHOOL(101,"你还在上中学！");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

