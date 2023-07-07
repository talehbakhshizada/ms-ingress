package az.company.msingress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MsIngressApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsIngressApplication.class, args);
    }

}

