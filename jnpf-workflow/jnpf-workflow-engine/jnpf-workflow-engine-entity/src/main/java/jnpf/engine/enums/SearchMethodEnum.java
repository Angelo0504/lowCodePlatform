package jnpf.engine.enums;

/**
 * 查询功能
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
public enum SearchMethodEnum {
    //like
    Contains("Contains"),
    //等于
    Equal("Equal"),
    //不等于
    NotEqual("NotEqual"),
    //小于
    LessThan("LessThan"),
    //小于等于
    LessThanOrEqual("LessThanOrEqual"),
    //大于
    GreaterThan("GreaterThan"),
    //大于等于
    GreaterThanOrEqual("GreaterThanOrEqual");

    private String message;

    SearchMethodEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
