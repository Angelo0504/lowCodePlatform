package jnpf.emnus;

/**
 * 数据库驱动枚举类
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
public enum  DbDriverEnum {
    /**
     * mysql
     */
    MYSQL("com.mysql.cj.jdbc.Driver"),
    /**
     * oracle
     */
    ORACLE("oracle.jdbc.OracleDriver"),
    /**
     * sqlserver
     */
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    private String dbDriver;

    DbDriverEnum(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }
}
