package com.dhanush.sapinventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sapInventoryOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:7073");
        localServer.setDescription("Local Development Server");

        Contact contact = new Contact();
        contact.setName("Dhanush");

        Info info = new Info()
                .title("SAP Inventory Service API")
                .version("1.0.0")
                .description("API documentation for the SAP Inventory Service. "
                        + "This service manages product inventory including adding items, "
                        + "checking inventory availability, blocking inventory for orders, "
                        + "and releasing/voiding inventory.")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
