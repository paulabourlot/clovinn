# clovinn
Prueba técnica - Clovinn

# Tecnologías usadas
Spring boot
Lombok (debe estar instalada la libería en eclipse para que el código compile)
Base de datos en memoria H2
Flyway
Thymeleaf

# Compilación del proyecto
1. Agregar lombok.jar a eclipse (https://projectlombok.org/setup/eclipse)
2. Ejecutar mvn compile del proyecto (esto generará la implementación del mapper)

# Ejecución del proyecto
1. Ejecutar proyecto Spring boot
2. En el navegador, ingresar a la URL http://localhost:8080
3. Alta de nuevo producto completando formulario
4. Alta de operación compra/venta 
 
# Testing
El lote completo de pruebas se puede lanzar ejecutando la clase IntegrationTestSuite con JUnit test.