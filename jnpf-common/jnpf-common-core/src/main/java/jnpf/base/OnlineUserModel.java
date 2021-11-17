package jnpf.base;

import lombok.Data;

import javax.websocket.Session;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
@Data
public class OnlineUserModel {
    /**
     *  连接Id
     */
    private String connectionId;
    /**
     *  用户Id
     */
    private String userId;
    /**
     *  租户Id
     */
    private String tenantId;

    public String getTenantId() {
        return tenantId = tenantId == null ? "" : tenantId;
    }

    /**
     *  移动端
     */
    private Boolean isMobileDevice;
    /**
     * session
     */
    private Session webSocket;
}
