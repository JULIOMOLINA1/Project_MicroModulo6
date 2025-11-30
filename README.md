# Sistema de Microservicios con Spring Boot

Este proyecto implementa una arquitectura de microservicios utilizando Spring Boot, Spring Cloud Gateway y PostgreSQL. 
Simula un sistema básico de e-commerce con servicios para gestionar usuarios, productos y órdenes.

## Arquitectura

El sistema está compuesto por los siguientes servicios:

*   API Gateway (9080): Punto de entrada único que enruta las peticiones a los microservicios correspondientes.
*   User Service (8081): Gestiona los usuarios.
*   Product Service (8082): Gestiona los productos.
*   Order Service (8083): Gestiona la creación y consulta de órdenes de compra. Integra información de usuarios y productos.

Cada microservicio cuenta con su propia base de datos PostgreSQL independiente, siguiendo el patrón Database por Service.

## Tecnologías

- Java 17
- Spring Boot
- Spring Cloud Gateway
- PostgreSQL 
- Flyway 
- Docker & Docker Compose 
- JUnit 5 & Mockito
- Maven

## Requisitos

- Docker y Docker Compose instalados.
- Java 17. 
- Maven .

## Instalación y Ejecución

La forma más sencilla de ejecutar el proyecto es utilizando Docker Compose, que levantará todos los servicios y bases de datos automáticamente.

1.  Clonar el repositorio (si aplica) o ubicarte en la raíz del proyecto.

2.  Construir y levantar los servicios:

    ```bash
    docker compose up --build
    ```

    Este comando descargará las imágenes necesarias, compilará los proyectos Java y levantará los contenedores.

    > Nota: La primera vez puede tardar unos minutos mientras Maven descarga las dependencias y construye los JARs.

3.  Verificar estado:

    ```bash
    docker compose ps
    ```

    Asegúrate de que todos los servicios (api-gateway-dev, user-service-dev, product-service-dev, order-service-dev) y bases de datos (postgres-*-dev) estén en estado Up o Healthy.

## Endpoints Principales

Todas las peticiones deben realizarse a través del API Gateway en el puerto 9080.

| Servicio | Método | Ruta API Gateway  | Descripción |
|:---------| :--- |:------------------| :--- |
| Users    | GET | /api/users        | Obtener todos los usuarios |
| Users    | GET | /api/users/{id}   | Obtener usuario por ID |
| Products | GET | /api/products/{id}| Obtener producto por ID |
| Orders   | POST | /api/orders       | Crear una nueva orden |
| Orders   | GET | /api/orders/{id}  | Obtener orden por ID |

### Ejemplo de creación de orden (JSON Body):

```json
POST http://localhost:9080/api/orders
Content-Type: application/json

{
  "userId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

> Datos Iniciales: Flyway carga automáticamente datos de prueba (users, products) al iniciar los servicios. Puedes usar IDs como 1, 2, etc.

## Ejecución de Pruebas

El proyecto incluye pruebas unitarias para los Servicios y Controladores. Las pruebas de integración complejas han sido deshabilitadas para facilitar la ejecución local rápida.

Para ejecutar las pruebas de un microservicio específico:

```bash
# Ejemplo para User Service:
cd user-service
./mvnw test
```


## Notas de Desarrollo

*   Testing: Se utiliza MockitoBean.


