package vn.sic.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"vn.sic.project.*"})
@EntityScan(basePackages = {"vn.sic.project.*"})
public class SiCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiCApplication.class, args);
    }

}
