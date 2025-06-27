# API de gestión de equipos de fútbol

API REST que permite administrar equipos de fútbol a través de operaciones CRUD, utilizando autenticación basada en  
tokens JWT para proteger el acceso.

## Endpoints

| HTTP method | URL                             |
|-------------|---------------------------------|
| GET         | /equipos                        |
| GET         | /equipos/{id}                   |
| GET         | /equipos/buscar?nombre={nombre} |
| POST        | /equipos                        |
| PUT         | /equipos/{id}                   |
| DELETE      | /equipos/{id}                   |

## Instrucciones de uso

Es necesario tener JDK instalado.

Con los siguientes comandos se ejecuta la app y los tests, respectivamente:
```
./gradlew bootRun
```
```
./gradlew test
```

La API quedará disponible en ```http://localhost:8080```.

Se puede interactuar con un cliente HTTP o bien a través del endpoint de Swagger: ```/swagger-ui/index.html```.  
Allí se encuentra información detallada sobre cada endpoint, incluyendo parámetros, respuestas y ejemplos.

### Autenticación

1) Autenticarse utilizando el endpoint ```/auth/login``` para obtener un token
2) Utilizar el token para acceder a los endpoints protegidos

Para realizar pruebas, se puede utilizar el siguiente usuario por defecto:

```json
{
  "username": "test",
  "password": "12345"
}
```
