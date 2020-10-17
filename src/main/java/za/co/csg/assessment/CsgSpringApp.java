package za.co.csg.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@SpringBootApplication
public class CsgSpringApp {

    public static void main(String[] args) {
        SpringApplication.run(CsgSpringApp.class, args);
    }
}
