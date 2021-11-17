package jnpf.form.model.letterservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发文单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 8:46
 */
@Data
public class LetterServiceForm {
    @ApiModelProperty(value = "相关附件")
    private String fileJson;
    @ApiModelProperty(value = "发文字号")
    private String issuedNum;
    @NotBlank(message = "流程标题不能为空")
    @ApiModelProperty(value = "流程标题")
    private String flowTitle;
    @NotNull(message = "紧急程度不能为空")
    @ApiModelProperty(value = "紧急程度")
    private Integer flowUrgent;
    @NotNull(message = "发文日期不能为空")
    @ApiModelProperty(value = "发文日期")
    private Long  writingDate;
    @ApiModelProperty(value = "主办单位")
    private String hostUnit;
    @ApiModelProperty(value = "抄送")
    private String copy;
    @ApiModelProperty(value = "发文标题")
    private String title;
    @ApiModelProperty(value = "主送")
    private String mainDelivery;
    @NotBlank(message = "流程主键不能为空")
    @ApiModelProperty(value = "流程主键")
    private String flowId;
    @NotBlank(message = "流程单据不能为空")
    @ApiModelProperty(value = "流程单据")
    private String billNo;
    @ApiModelProperty(value = "份数")
    private String shareNum;
    @ApiModelProperty(value = "提交/保存 0-1")
    private String status;
}
