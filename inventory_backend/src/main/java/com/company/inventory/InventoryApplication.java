package com.company.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ⭐ IMPORTA ESTO
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// ⭐ AGREGA ESTO ENCIMA DE @SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API de Inventario",
        version = "1.0",
        description = "API CRUD de Categorías y Productos para Programación Web II"
    )
)
@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

}
