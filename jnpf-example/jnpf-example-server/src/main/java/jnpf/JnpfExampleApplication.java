package jnpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JnpfExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfExampleApplication.class, args);
        System.out.println("example启动成功");
    }

}
