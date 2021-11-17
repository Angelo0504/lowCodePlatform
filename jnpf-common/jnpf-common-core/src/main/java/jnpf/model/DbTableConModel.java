package jnpf.model;

import lombok.Data;

/**
 * 数据建模DTO
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@Data
public class DbTableConModel {
    /**
     * 标识
     */
    private String id;
    /**
     * 表名
     */
    private String table;
    /**
     * 新表名
     */
    private String newTable;
    /**
     * 表说明
     */
    private String tableName;
    /**
     * 大小
     */
    private String size;
    /**
     * 总数
     */
    private Integer sum;
    /**
     * 说明
     */
    private String description;
    /**
     * 主键
     */
    private String primaryKey;
    /**
     * 数据源主键
     */
    private String dataSourceId;
}
