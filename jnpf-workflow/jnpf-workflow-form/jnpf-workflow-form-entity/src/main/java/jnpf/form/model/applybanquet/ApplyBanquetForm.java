package jnpf.form.model.applybanquet;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 宴请申请
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 8:46
 */
@Data
public class ApplyBanquetForm {
    @NotNull(message = "紧急程度不能为空")
    @ApiModelProperty(value = "紧急程度")
    private Integer flowUrgent;
    @ApiModelProperty(value = "预计费用",example = "1")
    private BigDecimal expectedCost;
    @ApiModelProperty(value = "备注")
    private String description;
    @ApiModelProperty(value = "宴请人员")
    private String banquetPeople;
    @NotBlank(message = "申请人员不能为空")
    @ApiModelProperty(value = "申请人员")
    private String applyUser;
    @NotBlank(message = "流程标题不能为空")
    @ApiModelProperty(value = "流程标题")
    private String flowTitle;
    @ApiModelProperty(value = "人员总数")
    private String total;
    @NotBlank(message = "所属职务不能为空")
    @ApiModelProperty(value = "所属职务")
    private String position;
    @ApiModelProperty(value = "宴请地点")
    private String place;
    @NotNull(message = "申请日期不能为空")
    @ApiModelProperty(value = "申请日期")
    private Long applyDate;
    @NotBlank(message = "流程主键不能为空")
    @ApiModelProperty(value = "流程主键")
    private String flowId;
    @NotBlank(message = "流程单据不能为空")
    @ApiModelProperty(value = "流程单据")
    private String billNo;
    @ApiModelProperty(value = "宴请人数")
    private String banquetNum;
    @ApiModelProperty(value = "提交/保存 0-1")
    private String status;

}
