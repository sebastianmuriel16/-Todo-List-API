# 📝 Todo List API - Spring Boot

Una **API RESTful** para la gestión de tareas (To-Do List), desarrollada con **Spring Boot**.  
Incluye autenticación de usuarios con JWT, validaciones, paginación y seguridad.

---

## 🚀 Características

- Registro e inicio de sesión de usuarios con JWT.
- CRUD completo de tareas (crear, listar, actualizar, eliminar).
- Validación de datos con `@Valid`.
- Manejo global de errores con `@ControllerAdvice`.
- Autenticación y autorización con Spring Security.
- Paginación y filtros con `Pageable`.
- Base de datos persistente (MySQL/PostgreSQL).
- Contraseñas seguras con BCrypt.

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA**
- **Hibernate Validator**
- **MySQL / PostgreSQL**
- **Flyway** para migraciones (opcional)
- **JUnit 5 + Mockito** para tests

---

## 📌 Endpoints principales

### 🔐 Autenticación

#### Registro
```http
POST /api/auth/register


Request

{
  "name": "John Doe",
  "email": "john@doe.com",
  "password": "password"
}


Response

{
  "token": "eyJhbGciOiJIUzI1..."
}

Login
POST /api/auth/login


Request

{
  "email": "john@doe.com",
  "password": "password"
}


Response

{
  "token": "eyJhbGciOiJIUzI1..."
}

✅ To-Do List
Crear tarea
POST /api/todos


Headers: Authorization: Bearer <token>
Request

{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}


Response

{
  "id": 1,
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}

Listar tareas (paginación)
GET /api/todos?page=0&size=10


Response

{
  "content": [
    {
      "id": 1,
      "title": "Buy groceries",
      "description": "Buy milk, eggs, bread"
    },
    {
      "id": 2,
      "title": "Pay bills",
      "description": "Pay electricity and water bills"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 2
}

Actualizar tarea
PUT /api/todos/1


Request

{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}


Response

{
  "id": 1,
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}


⚠️ Devuelve 403 Forbidden si el usuario no es el creador.

Eliminar tarea
DELETE /api/todos/1


Response:
204 No Content

⚙️ Instalación y ejecución

Clonar el repositorio:

git clone https://github.com/tuusuario/todo-list-api-springboot.git
cd todo-list-api-springboot


Configurar variables en application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=usuario
spring.datasource.password=clave
spring.jpa.hibernate.ddl-auto=update

jwt.secret=tu_clave_secreta
jwt.expiration=86400000


Compilar y ejecutar:

./mvnw spring-boot:run