package vn.sic.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"vn.sic.payment.*", "vn.sic.core.*", "vn.sic.log.*"})
@EntityScan(basePackages = {"vn.sic.payment.*", "vn.sic.log.*"})
@EnableMongoRepositories({"vn.sic.log.domain.repository"})
public class SiCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiCApplication.class, args);
    }

}
