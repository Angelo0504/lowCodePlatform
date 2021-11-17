package jnpf.form.model.order;

import jnpf.base.PaginationTime;
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
public class PaginationOrder extends PaginationTime {
    private  String enabledMark;
}
