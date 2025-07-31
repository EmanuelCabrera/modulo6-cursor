# Proyecto Full-Stack: Quarkus + Vue.js

Este proyecto consiste en un backend desarrollado con Quarkus y un frontend con Vue.js que se comunican entre sí.

## Estructura del Proyecto

```
modulo6-cursor/
├── Quarkus-app/          # Backend con Quarkus
└── vueJS-app/           # Frontend con Vue.js
```

## Requisitos Previos

- Java 17 o superior
- Node.js 16 o superior
- Maven 3.6 o superior

## Configuración y Ejecución

### 1. Backend (Quarkus)

1. Navega al directorio del backend:
   ```bash
   cd Quarkus-app
   ```

2. Ejecuta el backend en modo desarrollo:
   ```bash
   ./mvnw quarkus:dev
   ```

   El backend estará disponible en: `http://localhost:8080`

### 2. Frontend (Vue.js)

1. En una nueva terminal, navega al directorio del frontend:
   ```bash
   cd vueJS-app
   ```

2. Instala las dependencias:
   ```bash
   npm install
   ```

3. Ejecuta el frontend en modo desarrollo:
   ```bash
   npm run dev
   ```

   El frontend estará disponible en: `http://localhost:5173`

## Endpoints del Backend

### Autenticación
- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/register` - Registrar usuario

### Usuarios
- `GET /api/users` - Obtener todos los usuarios
- `GET /api/users/{id}` - Obtener usuario por ID

## Funcionalidades del Frontend

- **Autenticación**: Login y registro de usuarios
- **Dashboard**: Panel principal con información del usuario
- **Lista de Usuarios**: Muestra todos los usuarios registrados
- **Protección de Rutas**: Navegación protegida con guards de autenticación
- **Persistencia**: Estado de autenticación guardado en localStorage

## Credenciales de Demo

Para probar la aplicación, puedes usar estas credenciales:
- Email: `admin@example.com`
- Contraseña: `password`

O puedes registrar un nuevo usuario desde la interfaz.

## Tecnologías Utilizadas

### Backend
- **Quarkus**: Framework Java para aplicaciones nativas en la nube
- **Hibernate ORM**: Persistencia de datos
- **H2 Database**: Base de datos en memoria
- **JWT**: Autenticación con tokens
- **RESTEasy**: API REST

### Frontend
- **Vue.js 3**: Framework de JavaScript
- **Vue Router**: Enrutamiento
- **Pinia**: Gestión de estado
- **Axios**: Cliente HTTP
- **Vite**: Herramienta de construcción

## Configuración de CORS

El backend está configurado para permitir peticiones desde el frontend. La configuración CORS se encuentra en `Quarkus-app/src/main/resources/application.properties`.

## Desarrollo

### Backend
- Los controladores están en: `Quarkus-app/src/main/java/com/example/user/infrastructure/controller/`
- Los servicios en: `Quarkus-app/src/main/java/com/example/user/application/service/`
- Las entidades en: `Quarkus-app/src/main/java/com/example/user/domain/entity/`

### Frontend
- Los componentes en: `vueJS-app/src/views/`
- El store en: `vueJS-app/src/stores/`
- Los servicios API en: `vueJS-app/src/services/`
- El enrutador en: `vueJS-app/src/router/`

## Solución de Problemas

### Error de Conexión
Si el frontend no puede conectarse al backend:
1. Verifica que el backend esté ejecutándose en el puerto 8080
2. Verifica que no haya errores en la consola del backend
3. Comprueba que la URL de la API sea correcta en `vueJS-app/src/services/api.js`

### Error de CORS
Si hay errores de CORS:
1. Verifica la configuración CORS en `application.properties`
2. Asegúrate de que el backend esté ejecutándose
3. Reinicia el backend si es necesario

### Dependencias
Si hay problemas con las dependencias:
1. Ejecuta `npm install` en el directorio del frontend
2. Ejecuta `./mvnw clean install` en el directorio del backend 