package jnpf.form.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 成品入库单
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Data
@TableName("wform_finishedproduct")
public class FinishedProductEntity {
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
     * 入库人
     */
    @TableField("F_WAREHOUSENAME")
    private String warehouseName;

    /**
     * 仓库
     */
    @TableField("F_WAREHOUSE")
    private String warehouse;

    /**
     * 入库时间
     */
    @TableField("F_RESERVOIRDATE")
    private Date reservoirDate;

    /**
     * 备注
     */
    @TableField("F_DESCRIPTION")
    private String description;
}
