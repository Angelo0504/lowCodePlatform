package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 工作流启动类
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 11:32
 */
@SpringBootApplication
@EnableFeignClients
public class JnpfWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfWorkflowApplication.class, args);
        System.out.println("work启动成功");
    }

}
