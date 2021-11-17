package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "jnpf")
@EnableFeignClients(basePackages = "jnpf")
public class JnpfVisualdevApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfVisualdevApplication.class, args);
        System.out.println("visualdev启动成功");
    }

}
