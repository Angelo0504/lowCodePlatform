package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 鉴权启动程序
 *
 * @author jnpf
 */
@SpringBootApplication
@EnableFeignClients
public class JnpfOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfOauthApplication.class, args);
        System.out.println("鉴权启动成功");
    }

}
