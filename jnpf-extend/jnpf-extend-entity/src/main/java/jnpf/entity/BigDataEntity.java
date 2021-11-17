package jnpf.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 大数据测试
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Data
@TableName("ext_bigdata")
public class BigDataEntity {
    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 编码
     */
    @TableField("F_ENCODE")
    private String enCode;

    /**
     * 名称
     */
    @TableField("F_FULLNAME")
    private String fullName;

    /**
     * 描述
     */
    @TableField("F_DESCRIPTION")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "F_CREATORTIME",fill = FieldFill.INSERT)
    private Date creatorTime;
}
