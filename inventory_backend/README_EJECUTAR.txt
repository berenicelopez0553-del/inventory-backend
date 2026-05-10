BACKEND INVENTORY - VERSION CON IMAGEN BASE64

Cambios agregados para cumplir con la tabla product solicitada:
- account INT
- picture LONGTEXT para guardar imagen en Base64
- name VARCHAR(255)
- price DECIMAL
- category_id BIGINT como relación con category

También se conservan description y stock para no romper compatibilidad con el frontend anterior.

Base de datos configurada:
bd_inventory

Antes de ejecutar, revisa en src/main/resources/application.properties:
spring.datasource.username=root
spring.datasource.password=1234

Ejecutar:
.\mvnw clean spring-boot:run

Verificar en MySQL:
DESCRIBE product;
SELECT * FROM product;
