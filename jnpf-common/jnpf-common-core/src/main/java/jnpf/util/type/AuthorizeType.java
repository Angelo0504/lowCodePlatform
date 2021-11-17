package jnpf.util.type;

import lombok.Data;

/**
 * 权限类型常量表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
@Data
public class AuthorizeType {
    /**
     * 用户权限
     */
    public static final String USER = "User";
    /**
     * 岗位权限
     */
    public static final String POSITION = "Position";
    /**
     * 角色权限
     */
    public static final String ROLE = "Role";
    /**
     * 按钮权限
     */
    public static final String BUTTON = "button";
    /**
     * 菜单权限
     */
    public static final String MODULE = "module";
    /**
     * 列表权限
     */
    public static final String COLUMN = "column";
    /**
     * 数据权限
     */
    public static final String RESOURCE = "resource";
}
