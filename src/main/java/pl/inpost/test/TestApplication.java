package pl.inpost.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.inpost.test.configuration.OpenApiConfiguration;

@SpringBootApplication
@Import({OpenApiConfiguration.class})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
