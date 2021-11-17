package jnpf.form.model.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单信息
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 8:46
 */
@Data
public class OrderEntryListVO {
    @ApiModelProperty(value = "自然主键")
    private String id;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "规格型号")
    private String specifications;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "数量")
    private String qty;
    @ApiModelProperty(value = "单价")
    private String price;
    @ApiModelProperty(value = "金额")
    private String amount;
    @ApiModelProperty(value = " 折扣%")
    private String discount;
    @ApiModelProperty(value = " 税率%")
    private String cess;
    @ApiModelProperty(value = "实际单价")
    private String actualPrice;
    @ApiModelProperty(value = "实际金额")
    private String actualAmount;
    @ApiModelProperty(value = "描述")
    private String description;
}
