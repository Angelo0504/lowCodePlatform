package jnpf.form.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会议申请
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Data
@TableName("wform_applymeeting")
public class ApplyMeetingEntity {
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
     * 申请人员
     */
    @TableField("F_APPLYUSER")
    private String applyUser;

    /**
     * 所属职务
     */
    @TableField("F_POSITION")
    private String position;


    /**
     * 会议名称
     */
    @TableField("F_CONFERENCENAME")
    private String conferenceName;

    /**
     * 会议主题
     */
    @TableField("F_CONFERENCETHEME")
    private String conferenceTheme;

    /**
     * 会议类型
     */
    @TableField("F_CONFERENCETYPE")
    private String conferenceType;

    /**
     * 预计人数
     */
    @TableField("F_ESTIMATEPEOPLE")
    private String estimatePeople;

    /**
     * 会议室
     */
    @TableField("F_CONFERENCEROOM")
    private String conferenceRoom;

    /**
     * 管理人
     */
    @TableField("F_ADMINISTRATOR")
    private String administrator;

    /**
     * 查看人
     */
    @TableField("F_LOOKPEOPLE")
    private String lookPeople;

    /**
     * 纪要员
     */
    @TableField("F_MEMO")
    private String memo;

    /**
     * 出席人
     */
    @TableField("F_ATTENDEES")
    private String attendees;

    /**
     * 申请材料
     */
    @TableField("F_APPLYMATERIAL")
    private String applyMaterial;

    /**
     * 预计金额
     */
    @TableField("F_ESTIMATEDAMOUNT")
    private BigDecimal estimatedAmount;

    /**
     * 其他出席人
     */
    @TableField("F_OTHERATTENDEE")
    private String otherAttendee;

    /**
     * 开始时间
     */
    @TableField("F_STARTDATE")
    private Date startDate;

    /**
     * 结束时间
     */
    @TableField("F_ENDDATE")
    private Date endDate;

    /**
     * 相关附件
     */
    @TableField("F_FILEJSON")
    private String fileJson;

    /**
     * 会议描述
     */
    @TableField("F_DESCRIBE")
    @JSONField(name="describe")
    private String fdescribe;
}
