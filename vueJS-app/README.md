# Vue.js Login App

Una aplicación de frontend moderna construida con Vue.js 3 que incluye un sistema completo de autenticación con login.

## 🚀 Características

- **Vue.js 3** con Composition API
- **Vue Router 4** para navegación
- **Pinia** para gestión de estado
- **Vite** como bundler y dev server
- **UI moderna y responsive** con animaciones
- **Sistema de autenticación** completo
- **Protección de rutas** con guards
- **Persistencia de sesión** en localStorage
- **Diseño responsive** para móviles y desktop

## 📁 Estructura del Proyecto

```
vueJS-app/
├── src/
│   ├── components/          # Componentes reutilizables
│   ├── views/              # Páginas principales
│   │   ├── Login.vue       # Página de login
│   │   └── Dashboard.vue   # Dashboard post-login
│   ├── stores/             # Stores de Pinia
│   │   └── auth.js         # Store de autenticación
│   ├── services/           # Servicios de API
│   │   └── api.js          # Cliente HTTP y servicios
│   ├── config/             # Configuración
│   │   └── env.js          # Variables de entorno
│   ├── router/             # Configuración de rutas
│   │   └── index.js        # Definición de rutas
│   ├── App.vue             # Componente raíz
│   ├── main.js             # Punto de entrada
│   └── style.css           # Estilos globales
├── index.html              # HTML principal
├── package.json            # Dependencias y scripts
├── vite.config.js          # Configuración de Vite
├── env.example             # Ejemplo de variables de entorno
├── .gitignore              # Archivos ignorados por Git
└── README.md               # Este archivo
```

## 🛠️ Instalación

### Prerrequisitos
- **Node.js** (versión 16 o superior)
- **Backend Quarkus** ejecutándose en `http://localhost:8080`

### Frontend

1. **Instalar dependencias:**
   ```bash
   npm install
   ```

2. **Configurar variables de entorno:**
   Copia el archivo de ejemplo y configura tu API:
   ```bash
   cp env.example .env
   ```
   
   Edita el archivo `.env`:
   ```env
   VITE_API_BASE_URL=http://localhost:8080
   ```
   
   Si tu API está en un puerto diferente, cambia la URL en el archivo `.env`.

3. **Ejecutar en modo desarrollo:**
   ```bash
   npm run dev
   ```

4. **Construir para producción:**
   ```bash
   npm run build
   ```

5. **Previsualizar build de producción:**
   ```bash
   npm run preview
   ```

## 🔧 Configuración de Git

El proyecto incluye un `.gitignore` completo que excluye:

- **Dependencias**: `node_modules/`
- **Builds**: `dist/`, `build/`
- **Variables de entorno**: `.env*`
- **Archivos del sistema**: `.DS_Store`, `Thumbs.db`
- **Archivos de IDE**: `.vscode/`, `.idea/`
- **Logs y cache**: `*.log`, `.cache/`

### Para inicializar Git:
```bash
git init
git add .
git commit -m "Initial commit: Vue.js Login App"
```

## 🔐 Credenciales de Demo

Para probar la aplicación, usa estas credenciales:

- **Email:** `admin@example.com`
- **Contraseña:** `admin123`

O registra un nuevo usuario usando el formulario de registro.

## 🔌 Endpoints de la API

### Autenticación
- `POST /api/auth/register` - Registro de usuarios
- `POST /api/auth/login` - Login de usuarios

### Usuarios (requieren autenticación)
- `GET /api/users` - Obtener todos los usuarios
- `GET /api/users/{id}` - Obtener usuario por ID

## ⚙️ Variables de Entorno

La aplicación usa las siguientes variables de entorno:

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `VITE_API_BASE_URL` | URL base de la API | `http://localhost:8080` |
| `VITE_APP_TITLE` | Título de la aplicación | `Vue.js Login App` |

### Configuración por entorno:

**Desarrollo:**
```env
VITE_API_BASE_URL=http://localhost:8080
VITE_APP_TITLE=Vue.js Login App (Dev)
```

**Producción:**
```env
VITE_API_BASE_URL=https://api.tudominio.com
VITE_APP_TITLE=Vue.js Login App
```

### Archivo de Configuración

La aplicación incluye un archivo de configuración centralizado en `src/config/env.js` que:

- ✅ **Centraliza** todas las variables de entorno
- ✅ **Valida** la configuración al inicio
- ✅ **Proporciona** endpoints organizados
- ✅ **Maneja** diferentes entornos (dev/prod)
- ✅ **Muestra** logs informativos en consola

## 🎯 Funcionalidades

### Sistema de Autenticación
- **Login** con email/username y contraseña
- **Registro** de nuevos usuarios
- Formulario dinámico que cambia entre login y registro
- Manejo de estados de carga
- Mensajes de error desde la API
- Animaciones y transiciones suaves

### Protección de Rutas
- Guards de navegación automáticos
- Redirección automática según estado de autenticación
- Rutas protegidas para usuarios autenticados

### Dashboard
- Información completa del usuario logueado
- Lista de usuarios del sistema (requiere autenticación)
- Funcionalidades de demo (ver información completa)
- Diseño moderno con cards informativos
- Botón de logout funcional

### Integración con API
- **API REST** con Quarkus backend
- **Autenticación JWT** real
- **Registro y login** de usuarios
- **Gestión de usuarios** (listar, obtener por ID)
- **Interceptores** para manejo automático de tokens
- **Manejo de errores** de la API
- **Persistencia** en localStorage

## 🎨 Diseño

La aplicación cuenta con un diseño moderno que incluye:

- **Gradientes** y efectos visuales atractivos
- **Animaciones** suaves en transiciones
- **Responsive design** para todos los dispositivos
- **Componentes** reutilizables y bien estructurados
- **UX optimizada** con feedback visual

## 🔧 Tecnologías Utilizadas

### Frontend
- **Vue.js 3** - Framework principal
- **Vue Router 4** - Enrutamiento
- **Pinia** - Gestión de estado
- **Vite** - Build tool y dev server
- **Axios** - Cliente HTTP para API
- **CSS3** - Estilos y animaciones
- **JavaScript ES6+** - Lógica de la aplicación

### Backend (API)
- **Quarkus** - Framework Java
- **JWT** - Autenticación
- **REST API** - Endpoints de autenticación y usuarios

### Configuración
- **Variables de entorno** - Configuración por ambiente
- **Configuración centralizada** - Archivo `src/config/env.js`
- **Validación automática** - Verificación de configuración al inicio

## 📱 Responsive Design

La aplicación está completamente optimizada para:

- **Desktop** (1200px+)
- **Tablet** (768px - 1199px)
- **Mobile** (hasta 767px)

## 🚀 Scripts Disponibles

- `npm run dev` - Servidor de desarrollo
- `npm run build` - Construcción para producción
- `npm run preview` - Previsualizar build de producción

## 🔄 Flujo de la Aplicación

1. **Inicio** → Redirección automática a `/login`
2. **Login** → Validación de credenciales
3. **Éxito** → Redirección a `/dashboard`
4. **Dashboard** → Información del usuario y funcionalidades
5. **Logout** → Limpieza de sesión y redirección a login

## 🎯 Próximas Mejoras

- [x] Integración con API real ✅
- [x] Registro de usuarios ✅
- [ ] Recuperación de contraseña
- [ ] Perfil de usuario editable
- [ ] Temas oscuro/claro
- [ ] Notificaciones push
- [ ] Tests unitarios
- [ ] Paginación en lista de usuarios
- [ ] Filtros y búsqueda
- [ ] Edición de usuarios
- [ ] Eliminación de usuarios

## 📄 Licencia

MIT License - Libre para uso personal y comercial.

---

¡Disfruta usando la aplicación! 🎉 

# Tests del Frontend Vue.js

## Estado Actual ✅

Todos los tests están funcionando correctamente. Se han implementado tests básicos y sencillos que cubren la funcionalidad principal de la aplicación.

## Tests Implementados

### 1. Store de Autenticación (`src/stores/__tests__/auth.test.js`)
- ✅ **5 tests pasando**
- Tests del estado inicial del store
- Tests de los getters (isAuthenticated, currentUser)
- Tests de la acción logout

### 2. Servicios API (`src/services/__tests__/api.test.js`)
- ✅ **2 tests pasando**
- Tests básicos de existencia de servicios

### 3. Componente Login (`src/views/__tests__/Login.test.js`)
- ✅ **6 tests pasando**
- Tests de renderizado del formulario
- Tests de interacción con inputs
- Tests de validación de campos requeridos

### 4. Componente Dashboard (`src/views/__tests__/Dashboard.test.js`)
- ✅ **4 tests pasando**
- Tests de renderizado del dashboard
- Tests de funcionalidad de logout
- Tests de mensajes de bienvenida

### 5. Router (`src/router/__tests__/index.test.js`)
- ✅ **4 tests pasando**
- Tests de configuración de rutas
- Tests de navegación entre rutas

### 6. Tests de Integración (`src/test/integration/auth-flow.test.js`)
- ✅ **4 tests pasando**
- Tests de integración entre componentes
- Tests de flujo de autenticación

## Resumen Total
- **6 archivos de test**
- **25 tests pasando**
- **0 tests fallando**

## Comandos de Ejecución

```bash
# Ejecutar todos los tests
npm test

# Ejecutar tests una vez
npm test -- --run

# Ejecutar tests con UI
npm run test:ui

# Ejecutar tests con cobertura
npm run test:coverage
```

## Configuración

Los tests están configurados con:
- **Vitest** como test runner
- **@vue/test-utils** para testing de componentes Vue
- **jsdom** como entorno DOM
- **Pinia** para testing de stores

## Notas

- Los tests están diseñados para ser simples y confiables
- Se evitan mocks complejos que puedan causar problemas
- Los warnings sobre inyección de router son normales en tests unitarios
- Los errores de red en stderr son esperados ya que el backend no está corriendo durante los tests

## Próximos Pasos

Si se desea expandir los tests, se pueden agregar:
1. Tests más específicos para cada funcionalidad
2. Tests de edge cases
3. Tests de integración más complejos
4. Tests de performance
5. Tests de accesibilidad