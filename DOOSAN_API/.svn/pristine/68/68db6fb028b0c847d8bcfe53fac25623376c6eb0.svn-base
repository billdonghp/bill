package com.ait.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户数据的类
 */
@Data
@ApiModel(value="审批查询条件",description="审批查询用的对象")
public class AffirmQuery {

    @ApiModelProperty(value="审批类型：休假（leaveAffirm）,加班（otAffirm），考勤修改（arModifyAffirm）,礼品审批（presentAffirm），" +
            "快件审批（expressAffirm），公章审批（sealAffirm），临时卡审批（tempCardAffirm），参观者审批（visiterAffirm），签证审批（visaAffirm），"  +
            "工人评价（evsAffirm），评价成绩审批（evsResultAffirm）",name="content",required=true,example="leaveAffirm" ,dataType = "string")
    private String content;

    @ApiModelProperty(value="区分：待审批（2），已审批（1），默认查询待审批信息，详情查询的时候不需要此字段",name="qryType", example="2" ,dataType = "string")
    private String qryType;

    @ApiModelProperty(value="查询关键字",name="key" ,example="张三" ,dataType = "string")
    private String key;

    @ApiModelProperty(value="已审批页面，下拉刷新加载数据使用，一页20个，待审批页面查询所有，详情查询的时候不需要此字段",name="currentPage",example="0" ,dataType = "string")
    private String currentPage;

    @ApiModelProperty(value="详情查询使用的审批信息ID" ,dataType = "string")
    private String applyNo;

    @ApiModelProperty(value="详情查询使用的审批类型：LeaveApply(休假申请)，OtApply（加班申请），arModify（考勤修改）,present（礼物）,express(快件)" +
            " seal（公章），tempCard（临时卡），visiter（参观者），visa（签证），ev（工人评价），evs（评价成绩审批）" ,example="LeaveApply" ,dataType = "string")
    private String applyType;

    @ApiModelProperty(value="用户",hidden=true)
    private String username;

    @ApiModelProperty(value="工人评价使用,部门ID" ,dataType = "string")
    private String deptId;
}
