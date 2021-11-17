package com.bstek.ureport.console;

import com.bstek.ureport.console.config.DataReportListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"jnpf","com.bstek.ureport.console"})
@ImportResource("classpath:ureport.xml")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "jnpf")
@MapperScan(basePackages = {"com.bstek.ureport.console.ureport.mapper"})
public class JnpfDataReportApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(JnpfDataReportApplication.class);
        //添加监听器
        springApplication.addListeners(new DataReportListener());
        springApplication.run(args);
    }

    @Bean
    public ServletRegistrationBean buildUreportServlet(){
        return new ServletRegistrationBean(new UReportServlet(), "/*");
    }

}
