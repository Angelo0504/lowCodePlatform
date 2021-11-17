package jnpf.model;

import jnpf.util.treeutil.SumTree;
import lombok.Data;

/**
 * 用户DTO
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Data
public class UserMenuModel extends SumTree {
    private String id;
    private String fullName;
    private Integer isButtonAuthorize;
    private Integer isColumnAuthorize;
    private Integer isDataAuthorize;
    private String enCode;
    private String parentId;
    private String icon;
    private String urlAddress;
    private String linkTarget;
    private Integer type;
    private Integer enabledMark;
    private Long sortCode;
    private String category;
    private String description;
    private String propertyJson;
}
