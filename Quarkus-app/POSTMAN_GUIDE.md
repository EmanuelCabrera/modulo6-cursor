# Guía de Uso - Collection Postman para Quarkus User API

## 📋 Descripción

Esta collection de Postman incluye todos los endpoints de la API REST de usuarios con Quarkus, organizados en carpetas para facilitar las pruebas.

## 🚀 Instalación

1. **Importar la Collection:**
   - Abre Postman
   - Haz clic en "Import"
   - Selecciona el archivo `Quarkus-User-API.postman_collection.json`
   - La collection se importará automáticamente

2. **Configurar Variables:**
   - En la collection, ve a la pestaña "Variables"
   - Verifica que `base_url` esté configurado como `http://localhost:8080`
   - La variable `auth_token` se llenará automáticamente después del login

## 📁 Estructura de la Collection

### 🔐 **Auth** - Endpoints de Autenticación
- **Register User**: Registra un nuevo usuario
- **Login User**: Inicia sesión con username
- **Register User with Email**: Registra un usuario administrador
- **Login with Email**: Inicia sesión usando email

### 👥 **Users** - Gestión de Usuarios (Requiere Auth)
- **Get All Users**: Obtiene todos los usuarios
- **Get User by ID**: Obtiene un usuario específico
- **Get User by ID (Not Found)**: Prueba usuario inexistente

### ❌ **Error Tests** - Pruebas de Errores
- **Register User - Invalid Data**: Datos de registro inválidos
- **Login - Invalid Credentials**: Credenciales incorrectas
- **Get Users - No Auth**: Acceso sin autenticación
- **Get Users - Invalid Token**: Token inválido

## 🧪 Flujo de Pruebas Recomendado

### 1. **Pruebas de Autenticación**
```bash
# 1. Registrar un usuario
POST /api/auth/register
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "fullName": "Test User"
}

# 2. Hacer login
POST /api/auth/login
{
  "usernameOrEmail": "testuser",
  "password": "password123"
}
```

### 2. **Pruebas de Usuarios (con token)**
```bash
# 3. Obtener todos los usuarios
GET /api/users
Authorization: Bearer <token>

# 4. Obtener usuario específico
GET /api/users/1
Authorization: Bearer <token>
```

### 3. **Pruebas de Errores**
```bash
# 5. Probar datos inválidos
POST /api/auth/register
{
  "username": "",
  "email": "invalid-email",
  "password": "123",
  "fullName": ""
}

# 6. Probar credenciales incorrectas
POST /api/auth/login
{
  "usernameOrEmail": "nonexistent",
  "password": "wrongpassword"
}
```

## 🔧 Configuración de Variables

### Variables de la Collection:
- **`base_url`**: URL base de la API (`http://localhost:8080`)
- **`auth_token`**: Token JWT (se llena automáticamente)

### Para configurar manualmente:
1. Ve a la collection
2. Pestaña "Variables"
3. Modifica `base_url` si es necesario
4. `auth_token` se llenará automáticamente después del login

## 📊 Respuestas Esperadas

### ✅ **Registro Exitoso (200)**
```json
{
  "message": "User registered successfully",
  "user": {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "fullName": "Test User",
    "isActive": true,
    "createdAt": "2024-01-01T00:00:00"
  }
}
```

### ✅ **Login Exitoso (200)**
```json
{
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "fullName": "Test User"
  }
}
```

### ✅ **Obtener Usuarios (200)**
```json
[
  {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "fullName": "Test User",
    "isActive": true,
    "createdAt": "2024-01-01T00:00:00"
  }
]
```

### ❌ **Errores Comunes (400/401/404)**
```json
{
  "message": "Error description"
}
```

## 🚨 Códigos de Estado HTTP

- **200**: Operación exitosa
- **201**: Recurso creado exitosamente
- **400**: Datos inválidos o error de validación
- **401**: No autorizado (token inválido o faltante)
- **404**: Recurso no encontrado
- **409**: Conflicto (usuario ya existe)
- **500**: Error interno del servidor

## 💡 Tips de Uso

1. **Orden de Pruebas:**
   - Siempre ejecuta primero el registro
   - Luego el login para obtener el token
   - Finalmente las pruebas de usuarios

2. **Manejo de Tokens:**
   - El token se guarda automáticamente en la variable `auth_token`
   - Los endpoints protegidos lo usan automáticamente

3. **Pruebas de Errores:**
   - Ejecuta las pruebas de error para verificar validaciones
   - Verifica que los mensajes de error sean claros

4. **Variables de Entorno:**
   - Puedes cambiar `base_url` para diferentes entornos
   - Para producción: `https://tu-api.com`

## 🔍 Debugging

### Si las pruebas fallan:

1. **Verifica que la aplicación esté corriendo:**
   ```bash
   mvn quarkus:dev
   ```

2. **Revisa los logs de Quarkus** para errores

3. **Verifica las variables de la collection**

4. **Prueba con curl** para confirmar que la API funciona:
   ```bash
   curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username":"test","email":"test@example.com","password":"123","fullName":"Test"}'
   ```

## 📝 Notas Adicionales

- La collection incluye ejemplos de datos reales
- Los tokens JWT expiran en 24 horas por defecto
- La base de datos H2 se reinicia en cada ejecución
- CORS está habilitado para desarrollo

¡Listo para probar tu API! 🎉 