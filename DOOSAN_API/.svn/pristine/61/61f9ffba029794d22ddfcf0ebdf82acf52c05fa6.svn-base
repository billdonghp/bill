package com.ait.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户数据的类
 */
@Data
@ApiModel(value="审批参数对象",description="审批所需参数")
public class Affirm {

    @ApiModelProperty(value="审批类型：休假（leaveAffirm）,加班（otAffirm），考勤修改（arModifyAffirm）,礼品审批（presentAffirm），" +
            "快件审批（expressAffirm），公章审批（sealAffirm），临时卡审批（tempCardAffirm），参观者审批（visiterAffirm），签证审批（visaAffirm），"  +
            "工人评价（evsAffirm），评价成绩审批（evsResultAffirm）",required=true,example="leaveAffirm" ,dataType = "string")
    private String content;

    @ApiModelProperty(value="审批号，多个以逗号分隔",example="123,456")
    private String affirmNo;

    @ApiModelProperty(value="审批标识:1通过，2否决。",required=true,example="1")
    private String flag;

    @ApiModelProperty(value="审批备注",example="批准，辛苦了。")
    private String affirmRemark;

    @ApiModelProperty(value="用户",hidden=true)
    private String username;

}
