package jnpf.base.vo;

import lombok.Data;

/**
 * 需要分页的模型
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-15 09:50
 */
@Data
public class PaginationVO {
    private Long currentPage;
    private Long pageSize;
    private Integer total;
}
