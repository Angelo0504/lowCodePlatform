package jnpf.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 单据规则
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Data
@TableName("base_billrule")
public class BillRuleEntity {
    /**
     * 单据主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 单据名称
     */
    @TableField("F_FULLNAME")
    private String fullName;

    /**
     * 单据编码
     */
    @TableField("F_ENCODE")
    private String enCode;

    /**
     * 单据前缀
     */
    @TableField("F_PREFIX")
    private String prefix;

    /**
     * 日期格式
     */
    @TableField("F_DATEFORMAT")
    private String dateFormat;

    /**
     * 流水位数
     */
    @TableField("F_DIGIT")
    private Integer digit;

    /**
     * 流水起始
     */
    @TableField("F_STARTNUMBER")
    private String startNumber;

    /**
     * 流水范例
     */
    @TableField("F_EXAMPLE")
    private String example;

    /**
     * 当前流水号
     */
    @TableField("F_THISNUMBER")
    private Integer thisNumber;

    /**
     * 输出流水号
     */
    @TableField("F_OUTPUTNUMBER")
    private String outputNumber;

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
    private Integer enabledMark;

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
     * 删除标志
     */
    @TableField("F_DELETEMARK")
    private Integer deleteMark;
}
