package jnpf.base;

import lombok.Data;

import java.util.Date;

/**
 * 登录者信息
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Data
public class UserInfo {
    /**
     *唯一Id
     */
    private String id;
    /**
     *用户主键
     */
    private String userId;
    /**
     *用户账户
     */
    private String userAccount;
    /**
     *用户姓名
     */
    private String userName;
    /**
     *用户头像
     */
    private String userIcon;
    /**
     *用户性别
     */
    private String userGender;
    /**
     *机构主键
     */
    private String organizeId;
    /**
     *机构主键
     */
    private String departmentId;
    /**
     *我的主管
     */
    private String managerId;
    /**
     *下属机构
     */
    private String[] subOrganizeIds;
    /**
     *我的下属
     */
    private String[] subordinateIds;
    /**
     *岗位主键
     */
    private String[] positionIds;
    /**
     *角色主键
     */
    private String[] roleIds;
    /**
     *登录时间
     */
    private String loginTime;
    /**
     *登录IP地址
     */
    private String loginIpAddress;
    /**
     *登录IP地址所在城市
     */
    private String loginIpAddressName;
    /**
     *登录MAC地址
     */
    private String macAddress;
    /**
     *登录平台设备
     */
    private String loginPlatForm;
    /**
     *上次登录时间
     */
    private Date prevLoginTime;
    /**
     *上次登录IP地址
     */
    private String prevLoginIpAddress;
    /**
     *上次登录IP地址所在城市
     */
    private String prevLoginIpAddressName;
    /**
     *是否超级管理员
     */
    private Boolean isAdministrator = true;
    /**
     * 无操作多久过期
     */
    private Integer tokenTimeout;
    /**
     *过期时间
     */
    private Date overdueTime;
    /**
     *租户编码
     */
    private String tenantId;
    public String getTenantId() {
        return tenantId = tenantId == null ? "" : tenantId;
    }
    /**
     *租户数据库连接串（注意：主要解决多租户系统用的。每个租户连接数据库都是唯一的）
     */
    /**
     *目前就支持一个数据库。如果业务需要多个数据库，手动去添加 ConnectionString1、ConnectionString2 等等
     */
    private String tenantDbConnectionString;
    /**
     *租户数据库类型
     */
    private String tenantDbType;
    private String portalId;
}
