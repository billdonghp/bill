package com.ait.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户数据的类
 */
@Data
@ApiModel(value="查看页面查询条件对象")
public class ApplyQuery {

    @ApiModelProperty(value="申请类型：休假（leaveApplyInfo）,加班（otApplyInfo），公章（sealApplyInfo）,快件（expressApplyInfo）",name="content",required=true,example="sealApplyInfo" ,dataType = "string")
    private String content;

    @ApiModelProperty(value="区分：待审批（2），已审批（1），默认查询待审批信息，详情查询的时候不需要此字段",name="qryType", example="2" ,dataType = "string")
    private String qryType;

    @ApiModelProperty(value="查询关键字",name="key" ,example="张三" ,dataType = "string")
    private String key;

    @ApiModelProperty(value="已审批页面，下拉刷新加载数据使用，一页20个，待审批页面查询所有，详情查询的时候不需要此字段",name="currentPage",example="0" ,dataType = "string")
    private String currentPage;

    @ApiModelProperty(value="详情查询使用的审批信息ID" ,dataType = "string")
    private String applyNo;

    @ApiModelProperty(value="详情查询使用的审批类型：LeaveApply(休假申请)，OtApply（加班申请），公章（seal）,快件（express）" ,example="LeaveApply" ,dataType = "string")
    private String applyType;

    @ApiModelProperty(value="用户",hidden=true)
    private String username;
}
