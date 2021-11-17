package jnpf.form.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 借支单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Data
@TableName("wform_debitbill")
public class DebitBillEntity {
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
     * 工作部门
     */
    @TableField("F_DEPARTMENTAL")
    private String departmental;

    /**
     * 申请日期
     */
    @TableField("F_APPLYDATE")
    private Date applyDate;

    /**
     * 员工姓名
     */
    @TableField("F_STAFFNAME")
    private String staffName;

    /**
     * 员工职务
     */
    @TableField("F_STAFFPOST")
    private String staffPost;

    /**
     * 员工编码
     */
    @TableField("F_STAFFID")
    private String staffId;

    /**
     * 借款方式
     */
    @TableField("F_LOANMODE")
    private String loanMode;

    /**
     * 借支金额
     */
    @TableField("F_AMOUNTDEBIT")
    private BigDecimal amountDebit;

    /**
     * 转账账户
     */
    @TableField("F_TRANSFERACCOUNT")
    private String transferAccount;

    /**
     * 还款票据
     */
    @TableField("F_REPAYMENTBILL")
    private String repaymentBill;

    /**
     * 还款日期
     */
    @TableField("F_TEACHINGDATE")
    private Date teachingDate;

    /**
     * 支付方式
     */
    @TableField("F_PAYMENTMETHOD")
    private String paymentMethod;

    /**
     * 借款原因
     */
    @TableField("F_REASON")
    private String reason;
}
