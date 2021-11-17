package jnpf.form.model.order;

import com.alibaba.fastjson.annotation.JSONField;
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
public class OrderReceivableListVO {
    @ApiModelProperty(value = "自然主键")
    private String id;
    @ApiModelProperty(value = "收款日期")
    private Long receivableDate;
    @ApiModelProperty(value = "收款比率")
    private Integer receivableRate;
    @ApiModelProperty(value = "收款金额")
    private String receivableMoney;
    @ApiModelProperty(value = "收款方式")
    private String receivableMode;
    @ApiModelProperty(value = "收款摘要")
    @JSONField(name = "abstract")
    private String fabstract;
}
