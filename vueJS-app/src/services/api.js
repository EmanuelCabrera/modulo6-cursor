import axios from 'axios'
import { config } from '../config/env'

const apiClient = axios.create({
  baseURL: config.API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})

// Interceptor para agregar token de autenticación
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Interceptor para manejar errores de respuesta
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Token expirado o inválido
      localStorage.removeItem('token')
      localStorage.removeItem('user')
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

// Servicio de autenticación
export const authService = {
  async register(userData) {
    try {
      const response = await apiClient.post(config.ENDPOINTS.AUTH.REGISTER, {
        username: userData.username,
        email: userData.email,
        password: userData.password,
        fullName: userData.fullName
      })
      
      return {
        token: response.data.token,
        user: response.data.user
      }
    } catch (error) {
      throw error
    }
  },

  async login(credentials) {
    try {
      const response = await apiClient.post(config.ENDPOINTS.AUTH.LOGIN, {
        usernameOrEmail: credentials.email,
        password: credentials.password
      })
      
      return {
        token: response.data.token,
        user: response.data.user
      }
    } catch (error) {
      throw error
    }
  }
}

// Servicio de usuarios
export const userService = {
  async getAllUsers() {
    try {
      const response = await apiClient.get(config.ENDPOINTS.USERS.ALL)
      return response.data
    } catch (error) {
      throw error
    }
  },

  async getUserById(id) {
    try {
      const response = await apiClient.get(config.ENDPOINTS.USERS.BY_ID(id))
      return response.data
    } catch (error) {
      throw error
    }
  }
}

export default apiClient 