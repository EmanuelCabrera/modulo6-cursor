# API REST de Usuarios con Quarkus

Esta es una API REST desarrollada con Quarkus que implementa registro y login de usuarios, aplicando principios SOLID y utilizando una base de datos H2 en memoria.

## Características

- **Arquitectura SOLID**: Implementación siguiendo principios de diseño SOLID
- **Base de datos en memoria**: H2 para desarrollo y pruebas
- **Autenticación JWT**: Tokens JWT para autenticación
- **Validación**: Validación de datos de entrada
- **Encriptación de contraseñas**: BCrypt para seguridad
- **API REST**: Endpoints RESTful para registro y login

## Estructura del Proyecto

```
src/main/java/com/example/user/
├── domain/
│   ├── entity/
│   │   └── User.java
│   ├── dto/
│   │   ├── UserRegistrationRequest.java
│   │   ├── LoginRequest.java
│   │   ├── UserResponse.java
│   │   └── AuthResponse.java
│   ├── repository/
│   │   └── UserRepository.java
│   └── service/
│       └── AuthService.java
├── application/
│   └── service/
│       └── AuthServiceImpl.java
└── infrastructure/
    ├── repository/
    │   └── UserRepositoryImpl.java
    ├── security/
    │   ├── PasswordService.java
    │   └── JwtService.java
    └── controller/
        ├── AuthController.java
        └── UserController.java
```

## Principios SOLID Aplicados

### 1. Single Responsibility Principle (SRP)
- Cada clase tiene una única responsabilidad
- `AuthService`: Manejo de autenticación
- `PasswordService`: Encriptación de contraseñas
- `JwtService`: Generación y validación de tokens

### 2. Open/Closed Principle (OCP)
- Interfaces extensibles sin modificar código existente
- `UserRepository` permite diferentes implementaciones

### 3. Liskov Substitution Principle (LSP)
- `UserRepositoryImpl` puede sustituir a `UserRepository`
- Implementaciones intercambiables

### 4. Interface Segregation Principle (ISP)
- Interfaces específicas para cada funcionalidad
- `AuthService` separado de `UserRepository`

### 5. Dependency Inversion Principle (DIP)
- Dependencias hacia abstracciones, no implementaciones
- Inyección de dependencias con CDI

## Endpoints de la API

### Registro de Usuario
```
POST /api/auth/register
Content-Type: application/json

{
  "username": "usuario",
  "email": "usuario@example.com",
  "password": "password123",
  "fullName": "Usuario Ejemplo"
}
```

### Login de Usuario
```
POST /api/auth/login
Content-Type: application/json

{
  "usernameOrEmail": "usuario",
  "password": "password123"
}
```

### Obtener Todos los Usuarios
```
GET /api/users
Authorization: Bearer <token>
```

### Obtener Usuario por ID
```
GET /api/users/{id}
Authorization: Bearer <token>
```

## Ejecutar la Aplicación

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior

### Comandos

1. **Compilar y ejecutar en modo desarrollo:**
```bash
mvn quarkus:dev
```

2. **Compilar:**
```bash
mvn compile
```

3. **Ejecutar tests:**
```bash
mvn test
```

4. **Crear ejecutable:**
```bash
mvn package
```

## Configuración

La aplicación utiliza H2 en memoria por defecto. La configuración se encuentra en `src/main/resources/application.properties`:

- Puerto: 8080
- Base de datos: H2 en memoria
- JWT: Configurado con clave secreta
- CORS: Habilitado para desarrollo

### Configuración JWT

Las variables de configuración JWT se encuentran en `src/main/resources/application.properties`:

- `jwt.secret`: Secreto para firmar los tokens JWT (por defecto: un valor de desarrollo)
- `jwt.expiration`: Duración del token en milisegundos (por defecto: 86400000 = 24 horas)

Puedes modificar estos valores directamente en el archivo `application.properties`:

```properties
# JWT configuration
jwt.secret=tu_secreto_super_seguro_aqui
jwt.expiration=86400000
```

También puedes usar variables de entorno para sobrescribir estos valores:
- `JWT_SECRET`: Para el secreto JWT
- `JWT_EXPIRATION`: Para la duración del token

## Ejemplos de Uso

### Registro de Usuario
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "testuser",
    "password": "password123"
  }'
```

### Obtener Usuarios (con token)
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <token>"
```

## Tecnologías Utilizadas

- **Quarkus**: Framework Java nativo para la nube
- **Hibernate ORM**: Persistencia de datos
- **H2**: Base de datos en memoria
- **JWT**: Autenticación con tokens
- **BCrypt**: Encriptación de contraseñas
- **Jakarta REST**: API REST
- **CDI**: Inyección de dependencias
- **Bean Validation**: Validación de datos 