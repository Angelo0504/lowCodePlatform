package jnpf.form.model.procurementmaterial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 采购原材料
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 8:46
 */
@Data
public class ProcurementMaterialForm {
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "流程主键")
    private String flowId;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "流程标题")
    private String flowTitle;
    @NotNull(message = "必填")
    @ApiModelProperty(value = "紧急程度")
    private Integer flowUrgent;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "流程单据")
    private String billNo;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "申请人")
    private String applyUser;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "申请部门")
    private String departmental;
    @NotNull(message = "必填")
    @ApiModelProperty(value = "申请日期")
    private Long applyDate;
    @ApiModelProperty(value = "采购单位")
    private String purchaseUnit;
    @ApiModelProperty(value = "送货方式")
    private String deliveryMode;
    @ApiModelProperty(value = "送货地址")
    private String deliveryAddress;
    @NotBlank(message = "必填")
    @ApiModelProperty(value = "付款方式")
    private String paymentMethod;
    @ApiModelProperty(value = "付款金额")
    private BigDecimal paymentMoney;
    @ApiModelProperty(value = "相关附件")
    private String fileJson;
    @ApiModelProperty(value = "用途原因")
    private String reason;
    @ApiModelProperty(value = "提交/保存 0-1")
    private String status;
    @ApiModelProperty(value = "明细")
    List<ProcurementEntryEntityInfoModel> entryList;
}
