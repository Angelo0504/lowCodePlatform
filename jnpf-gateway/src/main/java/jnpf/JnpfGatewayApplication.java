package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动程序
 *
 * @author jnpf
 */
@SpringBootApplication
@EnableDiscoveryClient
public class JnpfGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfGatewayApplication.class, args);
        System.out.println("网关启动成功");
    }

}
