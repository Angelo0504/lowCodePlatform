package jnpf.form.model.materialrequisition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 领料单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 8:46
 */
@Data
public class MaterialEntryEntityInfoModel {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "领料主键")
    private String leadeId;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "规格型号")
    private String specifications;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "需数量")
    private String materialDemand;
    @ApiModelProperty(value = "配数量")
    private String proportioning;
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;
    @ApiModelProperty(value = "备注")
    private String description;
    @ApiModelProperty(value = "排序码")
    private Long sortCode;
}
