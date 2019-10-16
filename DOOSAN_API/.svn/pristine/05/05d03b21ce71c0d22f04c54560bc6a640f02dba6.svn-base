package com.ait.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公章
 *
 * @author wei
 * @date 2015/7/31.
 */
@Data
@ApiModel(value = "公章申请对象")
public class Seal {

    @ApiModelProperty(value = "编号", required = true, example = "GZ19080701")
    private String documentno;

    @ApiModelProperty(value = "申请者法人ID", required = true)
    private String cpnyId;

    @ApiModelProperty(value = "申请者ID，系统内部使用", required = true)
    private String personId;

    @ApiModelProperty(value = "申请者ID，系统内部使用（同上）", hidden = true)
    private String applerId;

    @ApiModelProperty(value = "申请者部门ID", required = true)
    private String deptId;

    @ApiModelProperty(value = "是否外借：1是，0否", required = true, example = "0")
    private String isLend;

    @ApiModelProperty(value = "公章法人：59000000（DICI），63000000（DISD）,78000000(DICC),80000000(DCFL)", required = true, example = "59000000")
    private String companyId;

    @ApiModelProperty(value = "使用日期", required = true, example = "2019-08-08")
    private String useDate;

    @ApiModelProperty(value = "公章类型", required = true, example = "SealType_Code93")
    private String sealType;

    @ApiModelProperty(value = "盖章文件接收单位", required = true)
    private String selectDeptid;

    @ApiModelProperty(value = "使用内容(250个汉字以内)")
    private String useInformation;

    @ApiModelProperty(value = "份数")
    private String useShares;

    @ApiModelProperty(value = "备注(250个汉字以内)")
    private String note;

    @ApiModelProperty(value = "起案号")
    private String draftNo;

    @ApiModelProperty(value = "归还日期")
    private String returnDate;

    @ApiModelProperty(value = "不知道何用，暂时空着")
    private String affirmorIdea;

    @ApiModelProperty(value = "审批人personID，多个按顺序以逗号分隔")
    private String affirmorIdArr;

    @ApiModelProperty(value = "审批人职务ID，多个按顺序以逗号分隔")
    private String affirmorDutyArr;

    @ApiModelProperty(value = "操作类型", hidden = true)
    private String content = "sealApply";

    @ApiModelProperty(value = "登陆者信息", hidden = true)
    private String username;
}
