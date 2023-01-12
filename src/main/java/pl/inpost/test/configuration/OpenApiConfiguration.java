package pl.inpost.test.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "InPost Interview assignment API",
                contact = @Contact(
                        email = "hpawel91@gmail.com"
                ),
                version = "1.0.0"
        ),
        servers = {@Server(url = "http://127.0.0.1:8080/test", description = "LOCAL")}
)
@Configuration
public class OpenApiConfiguration {
}
