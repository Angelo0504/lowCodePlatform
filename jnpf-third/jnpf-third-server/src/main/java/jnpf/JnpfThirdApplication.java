package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "jnpf")
@EnableFeignClients
public class JnpfThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfThirdApplication.class, args);
        System.out.println("Third启动成功");
    }

}
