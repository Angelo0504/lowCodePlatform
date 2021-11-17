package jnpf.util.type;

/**
 * 请求方法枚举类
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
public enum MethodType {
    /**
     * GET请求
     */
    GET("GET"),
    /**
     * POST 请求
     */
    POST("POST"),
    /**
     * PUT 请求
     */
    PUT("PUT"),;
    private String method;

    MethodType(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
