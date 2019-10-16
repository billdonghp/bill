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
@ApiModel(value = "快件申请对象")
public class Express {

    @ApiModelProperty(value = "申请者法人ID", required = true)
    private String cpnyId;

    @ApiModelProperty(value = "申请者ID，系统内部使用", required = true)
    private String personId;

    @ApiModelProperty(value = "申请者ID，系统内部使用（同上）", hidden = true)
    private String applerId;

    @ApiModelProperty(value = "申请者ID，系统内部使用（同上）", hidden = true)
    private String adminId;

    @ApiModelProperty(value = "申请者部门ID", required = true)
    private String deptId;

    @ApiModelProperty(value = "发件人", required = true)
    private String SENDPERSON1;

    @ApiModelProperty(value = "费用(废除)",hidden = true)
    private String costs;

    @ApiModelProperty(value = "快件重量：0小于1公斤，1大于1公斤", required = true, example = "0")
    private String isOver_kg = "0";

    @ApiModelProperty(value = "发件城市（市）", required = true, example = "烟台市")
    private String citySent;
    @ApiModelProperty(value = "发件城市（省）", required = true, example = "山东省")
    private String citySentS;
    @ApiModelProperty(value = "发件城市（区）", required = true, example = "福山区")
    private String citySentQ;
    @ApiModelProperty(value = "发件城市（详细地址）", required = true, example = "开发区五指山路28号")
    private String citySentD;

    @ApiModelProperty(value = "发送详细地址（收件公司）", required = true, example = "阿里巴巴")
    private String postAddress;

    @ApiModelProperty(value = "默认EMS，不可以选择其他", required = true, example = "EMS")
    private String expressType = "EMS";

    @ApiModelProperty(value = "收件人", required = true, example = "张三")
    private String sendToPerson;

    @ApiModelProperty(value = "快件内容", required = true, example = "文件")
    private String postDescription;

    @ApiModelProperty(value = "寄达城市（市）", required = true, example = "南阳市")
    private String cityArrive;

    @ApiModelProperty(value = "寄达城市（省）", required = true, example = "河南省")
    private String cityArriveS;

    @ApiModelProperty(value = "寄达城市（区）", required = true, example = "新野县")
    private String cityArriveQ;

    @ApiModelProperty(value = "寄达城市（详细地址）", required = true, example = "汉城广场")
    private String cityArriveD;

    @ApiModelProperty(value = "快件类型（文件、礼品、其他等），下拉选择，代码：EXP_TYPE", required = true, example = "文件")
    private String expType;


    @ApiModelProperty(value = "发件人手机号", required = true, example = "15011111111")
    private String mobileS;

    @ApiModelProperty(value = "收件人手机号", required = true, example = "15011111111")
    private String mobileA;


    @ApiModelProperty(value = "发件人邮编", required = true, example = "264006")
    private String postCodeS;

    @ApiModelProperty(value = "收件人邮编", required = true, example = "100000")
    private String postCodeA;

    @ApiModelProperty(value = "审批人personID，多个按顺序以逗号分隔")
    private String affirmorIdArr;

    @ApiModelProperty(value = "审批人职务ID，多个按顺序以逗号分隔")
    private String affirmorDutyArr;

    @ApiModelProperty(value = "操作类型", hidden = true)
    private String content = "expressApply";

    @ApiModelProperty(value = "登陆者信息", hidden = true)
    private String username;

}
