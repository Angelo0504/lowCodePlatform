package jnpf;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 */
@EnableAdminServer
@SpringBootApplication
public class JnpfAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnpfAdminApplication.class, args);
    }

}
