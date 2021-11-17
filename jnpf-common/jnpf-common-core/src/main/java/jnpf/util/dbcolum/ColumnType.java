package jnpf.util.dbcolum;

import lombok.Data;

/**
 * 常用字段常量类
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-15 09:00
 */
@Data
public class ColumnType {

    /**
     *MySQL字段常量
     */
    public static final String MYSQL_VARCHAR = "varchar";

    public static final String MYSQL_DATETIME = "datetime";

    public static final String MYSQL_DECIMAL = "decimal";

    public static final String MYSQL_TEXT = "text";

    /**
     *Oracle字段常量
     */
    public static final String ORACLE_NVARCHAR = "NVARCHAR2";

    public static final String ORACLE_DATE = "DATE";

    public static final String ORACLE_DECIMAL = "DECIMAL";

    public static final String ORACLE_CLOB = "CLOB";

}
