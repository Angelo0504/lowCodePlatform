package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 多租户服务启动类
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-24
 */
@SpringBootApplication
@EnableFeignClients
public class JnpfTenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfTenantApplication.class, args);
        System.out.println("tenant启动成功");
    }

}
