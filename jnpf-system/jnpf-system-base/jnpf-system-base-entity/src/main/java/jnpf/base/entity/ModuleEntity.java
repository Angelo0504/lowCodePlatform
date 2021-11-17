package jnpf.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统功能
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Data
@TableName("base_module")
public class ModuleEntity {
    /**
     * 功能主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 功能上级
     */
    @TableField("F_PARENTID")
    private String parentId="0";

    /**
     * 功能类别
     */
    @TableField("F_TYPE")
    private Integer type;

    /**
     * 功能名称
     */
    @TableField("F_FULLNAME")
    private String fullName;

    /**
     * 功能编码
     */
    @TableField("F_ENCODE")
    private String enCode;

    /**
     * 功能地址
     */
    @TableField("F_URLADDRESS")
    private String urlAddress;

    /**
     * 按钮权限
     */
    @TableField("F_ISBUTTONAUTHORIZE")
    private Integer isButtonAuthorize;

    /**
     * 列表权限
     */
    @TableField("F_ISCOLUMNAUTHORIZE")
    private Integer isColumnAuthorize;

    /**
     * 数据权限
     */
    @TableField("F_ISDATAAUTHORIZE")
    private Integer isDataAuthorize;

    /**
     * 扩展属性
     */
    @TableField("F_PROPERTYJSON")
    private String propertyJson;

    /**
     * 描述
     */
    @TableField("F_DESCRIPTION")
    private String description;

    /**
     * 排序码
     */
    @TableField("F_SORTCODE")
    private Long sortCode;

    /**
     * 有效标志
     */
    @TableField("F_ENABLEDMARK")
    private Integer enabledMark=0;

    /**
     * 创建时间
     */
    @TableField(value = "F_CREATORTIME",fill = FieldFill.INSERT)
    private Date creatorTime;

    /**
     * 创建用户
     */
    @TableField(value = "F_CREATORUSERID",fill = FieldFill.INSERT)
    private String creatorUserId;

    /**
     * 修改时间
     */
    @TableField(value = "F_LASTMODIFYTIME",fill = FieldFill.UPDATE)
    private Date lastModifyTime;

    /**
     * 修改用户
     */
    @TableField(value = "F_LASTMODIFYUSERID",fill = FieldFill.UPDATE)
    private String lastModifyUserId;

    /**
     * 删除标志
     */
    @TableField("F_DELETEMARK")
    private Integer deleteMark;

    /**
     * 删除时间
     */
    @TableField("F_DELETETIME")
    private Date deleteTime;

    /**
     * 删除用户
     */
    @TableField("F_DELETEUSERID")
    private String deleteUserId;
    /**
     * 菜单图标
     */
    @TableField("F_ICON")
    private String icon;
    /**
     * 链接目标
     */
    @TableField("F_LINKTARGET")
    private String linkTarget;
    /**
     * 菜单分类 Web、App
     */
    @TableField("F_CATEGORY")
    private String category;
}
