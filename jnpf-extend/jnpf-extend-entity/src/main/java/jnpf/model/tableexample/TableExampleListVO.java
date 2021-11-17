package jnpf.model.tableexample;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class TableExampleListVO {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目阶段")
    private String projectPhase;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "负责人")
    private String principal;

    @ApiModelProperty(value = "立顶人")
    private String jackStands;

    @ApiModelProperty(value = "交付日期")
    private long interactionDate;

    @ApiModelProperty(value = "费用金额",example = "1")
    private Long costAmount;

    @ApiModelProperty(value = "已用金额",example = "1")
    private Long tunesAmount;

    @ApiModelProperty(value = "预计收入",example = "1")
    private Long projectedIncome;

    @ApiModelProperty(value = "登记人")
    private String registrant;
    @ApiModelProperty(value = "登记人")
    private long registerDate;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "批注")
    private String postilJson;
    @ApiModelProperty(value = "标记")
    private String sign;
    private String postilCount;
}
