package jnpf.model;

import lombok.Data;


/**
 *
 * BaseTenant模型
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-23
 */
@Data
public class BaseTenantCrForm  {
    private String enCode;

    private String fullName;

    private String companyName;

    private long expiresTime;

    private String description;

}
