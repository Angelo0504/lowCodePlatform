package jnpf.form.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 发文单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Data
@TableName("wform_letterservice")
public class LetterServiceEntity {
    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 流程主键
     */
    @TableField("F_FLOWID")
    private String flowId;

    /**
     * 流程标题
     */
    @TableField("F_FLOWTITLE")
    private String flowTitle;

    /**
     * 紧急程度
     */
    @TableField("F_FLOWURGENT")
    private Integer flowUrgent;

    /**
     * 流程单据
     */
    @TableField("F_BILLNO")
    private String billNo;

    /**
     * 主办单位
     */
    @TableField("F_HOSTUNIT")
    private String hostUnit;

    /**
     * 发文标题
     */
    @TableField("F_TITLE")
    private String title;

    /**
     * 发文字号
     */
    @TableField("F_ISSUEDNUM")
    private String issuedNum;

    /**
     * 发文日期
     */
    @TableField("F_WRITINGDATE")
    private Date writingDate;

    /**
     * 份数
     */
    @TableField("F_SHARENUM")
    private String shareNum;

    /**
     * 主送
     */
    @TableField("F_MAINDELIVERY")
    private String mainDelivery;

    /**
     * 抄送
     */
    @TableField("F_COPY")
    private String copy;

    /**
     * 相关附件
     */
    @TableField("F_FILEJSON")
    private String fileJson;
}
