// Configuración de variables de entorno
export const config = {
  // API Configuration
  API_BASE_URL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
  
  // App Configuration
  APP_TITLE: import.meta.env.VITE_APP_TITLE || 'Vue.js Login App',
  
  // Environment
  IS_DEVELOPMENT: import.meta.env.DEV,
  IS_PRODUCTION: import.meta.env.PROD,
  
  // API Endpoints
  ENDPOINTS: {
    AUTH: {
      LOGIN: '/api/auth/login',
      REGISTER: '/api/auth/register'
    },
    USERS: {
      ALL: '/api/users',
      BY_ID: (id) => `/api/users/${id}`
    }
  }
}

// Validación de configuración
export const validateConfig = () => {
  const requiredVars = ['API_BASE_URL']
  
  for (const varName of requiredVars) {
    if (!config[varName]) {
      console.warn(`⚠️ Variable de entorno ${varName} no está configurada`)
    }
  }
  
  console.log('🔧 Configuración cargada:', {
    API_BASE_URL: config.API_BASE_URL,
    APP_TITLE: config.APP_TITLE,
    ENVIRONMENT: config.IS_DEVELOPMENT ? 'Development' : 'Production'
  })
}

export default config 