package jnpf.engine.model.flowtask;

import jnpf.base.PaginationTime;
import lombok.Data;

/**
 *
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 9:17
 */
@Data
public class PaginationFlowTask extends PaginationTime {
  /**所属流程**/
  private String flowId;
  /**所属分类**/
  private String flowCategory;
  private String creatorUserId;
}
