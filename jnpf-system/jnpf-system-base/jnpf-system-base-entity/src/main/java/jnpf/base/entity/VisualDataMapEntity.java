package jnpf.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 大屏地图
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Data
@TableName("visualdata_map")
public class VisualDataMapEntity {

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 名称
     */
    @TableField("F_FULLNAME")
    private String fullName;

    /**
     * 编码
     */
    @TableField("F_ENCODE")
    private String enCode;

    /**
     * 地图数据
     */
    @TableField("F_Data")
    private String data;

    /**
     * 排序
     */
    @TableField("F_SORTCODE")
    private Long sortCode;

    /**
     * 有效标识
     */
    @TableField("F_ENABLEDMARK")
    private Integer enabledMark;

    /**
     * 创建时间
     */
    @TableField("F_CREATORTIME")
    private Date creatorTime;

    /**
     * 创建人
     */
    @TableField("F_CREATORUSERID")
    private String creatorUser;

    /**
     * 修改时间
     */
    @TableField("F_LASTMODIFYTIME")
    private Date lastModifyTime;

    /**
     * 修改人
     */
    @TableField("F_LASTMODIFYUSERID")
    private String lastModifyUser;

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
     * 删除人
     */
    @TableField("F_DELETEUSERID")
    private String deleteUserId;

}

