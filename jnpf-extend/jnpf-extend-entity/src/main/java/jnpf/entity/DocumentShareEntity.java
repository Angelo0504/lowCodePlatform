package jnpf.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 知识文档共享
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Data
@TableName("ext_documentshare")
public class DocumentShareEntity {
    /**
     * 共享主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 文档主键
     */
    @TableField("F_DOCUMENTID")
    private String documentId;

    /**
     * 共享人员
     */
    @TableField("F_SHAREUSERID")
    private String shareUserId;

    /**
     * 共享时间
     */
    @TableField("F_SHARETIME")
    private Date shareTime;
}
