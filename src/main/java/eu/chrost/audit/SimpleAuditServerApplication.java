package eu.chrost.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAuditServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAuditServerApplication.class, args);
    }

}
