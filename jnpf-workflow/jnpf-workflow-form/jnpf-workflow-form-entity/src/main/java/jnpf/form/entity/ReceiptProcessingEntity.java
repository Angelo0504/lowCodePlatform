package jnpf.form.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 收文处理表
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月29日 上午9:18
 */
@Data
@TableName("wform_receiptprocessing")
public class ReceiptProcessingEntity {
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
     * 文件标题
     */
    @TableField("F_FILETITLE")
    private String fileTitle;

    /**
     * 来文单位
     */
    @TableField("F_COMMUNICATIONUNIT")
    private String communicationUnit;

    /**
     * 来文字号
     */
    @TableField("F_LETTERNUM")
    private String letterNum;

    /**
     * 收文日期
     */
    @TableField("F_RECEIPTDATE")
    private Date receiptDate;

    /**
     * 相关附件
     */
    @TableField("F_FILEJSON")
    private String fileJson;
}
