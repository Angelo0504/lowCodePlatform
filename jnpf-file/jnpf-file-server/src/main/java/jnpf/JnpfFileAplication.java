package jnpf;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JnpfFileAplication {
    public static void main(String[] args) {
        SpringApplication.run(JnpfFileAplication.class,args);
        System.out.println("JnpfFile启动完成");
    }
}
