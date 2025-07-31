<template>
  <div class="login-container">
    <div class="login-card">
      <div class="login-header">
        <h1>{{ isLoginMode ? 'Bienvenido' : 'Registro' }}</h1>
        <p>{{ isLoginMode ? 'Inicia sesión en tu cuenta' : 'Crea tu cuenta' }}</p>
      </div>
      
      <form @submit.prevent="handleSubmit" class="login-form">
        <div class="form-group">
          <label for="email">Email</label>
          <input
            id="email"
            v-model="form.email"
            type="email"
            placeholder="tu@email.com"
            required
            :disabled="authStore.isLoading"
          />
        </div>
        
        <div class="form-group">
          <label for="password">Contraseña</label>
          <input
            id="password"
            v-model="form.password"
            type="password"
            placeholder="Tu contraseña"
            required
            :disabled="authStore.isLoading"
          />
        </div>
        
        <div v-if="!isLoginMode" class="form-group">
          <label for="username">Nombre de usuario</label>
          <input
            id="username"
            v-model="form.username"
            type="text"
            placeholder="Tu nombre de usuario"
            required
            :disabled="authStore.isLoading"
          />
        </div>
        
        <div v-if="!isLoginMode" class="form-group">
          <label for="fullName">Nombre completo</label>
          <input
            id="fullName"
            v-model="form.fullName"
            type="text"
            placeholder="Tu nombre completo"
            required
            :disabled="authStore.isLoading"
          />
        </div>
        
        <div v-if="authStore.error" class="error-message">
          {{ authStore.error }}
        </div>
        
        <button 
          type="submit" 
          class="login-button"
          :disabled="authStore.isLoading"
        >
          <span v-if="authStore.isLoading" class="loading-spinner"></span>
          {{ authStore.isLoading ? (isLoginMode ? 'Iniciando sesión...' : 'Registrando...') : (isLoginMode ? 'Iniciar Sesión' : 'Registrarse') }}
        </button>
      </form>
      
      <div class="mode-switch">
        <p>
          {{ isLoginMode ? '¿No tienes cuenta?' : '¿Ya tienes cuenta?' }}
          <button 
            type="button" 
            class="switch-button"
            @click="toggleMode"
            :disabled="authStore.isLoading"
          >
            {{ isLoginMode ? 'Regístrate' : 'Inicia sesión' }}
          </button>
        </p>
      </div>
      
      <div v-if="isLoginMode" class="demo-credentials">
        <p><strong>Credenciales de demo:</strong></p>
        <p>Email: admin@example.com</p>
        <p>Contraseña: admin123</p>
        <p>O registra un nuevo usuario</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

export default {
  name: 'Login',
  setup() {
    const router = useRouter()
    const authStore = useAuthStore()
    const isLoginMode = ref(true)
    
    const form = reactive({
      email: '',
      password: '',
      username: '',
      fullName: ''
    })
    
    const handleSubmit = async () => {
      if (isLoginMode.value) {
        const result = await authStore.login({
          email: form.email,
          password: form.password
        })
        if (result.success) {
          router.push('/dashboard')
        }
      } else {
        const result = await authStore.register({
          email: form.email,
          password: form.password,
          username: form.username,
          fullName: form.fullName
        })
        if (result.success) {
          router.push('/dashboard')
        }
      }
    }
    
    const toggleMode = () => {
      isLoginMode.value = !isLoginMode.value
      // Limpiar formulario al cambiar modo
      form.email = ''
      form.password = ''
      form.username = ''
      form.fullName = ''
      authStore.error = null
    }
    
    return {
      form,
      authStore,
      isLoginMode,
      handleSubmit,
      toggleMode
    }
  }
}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-card {
  background: white;
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  animation: slideUp 0.6s ease-out;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.login-header h1 {
  color: #333;
  margin: 0 0 10px 0;
  font-size: 28px;
  font-weight: 600;
}

.login-header p {
  color: #666;
  margin: 0;
  font-size: 16px;
}

.login-form {
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #333;
  font-weight: 500;
  font-size: 14px;
}

.form-group input {
  width: 100%;
  padding: 12px 16px;
  border: 2px solid #e1e5e9;
  border-radius: 10px;
  font-size: 16px;
  transition: all 0.3s ease;
  box-sizing: border-box;
}

.form-group input:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.form-group input:disabled {
  background-color: #f5f5f5;
  cursor: not-allowed;
}

.error-message {
  background-color: #fee;
  color: #c53030;
  padding: 12px;
  border-radius: 8px;
  margin-bottom: 20px;
  font-size: 14px;
  border: 1px solid #fecaca;
}

.login-button {
  width: 100%;
  padding: 14px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.login-button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 10px 20px rgba(102, 126, 234, 0.3);
}

.login-button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
  transform: none;
}

.loading-spinner {
  width: 16px;
  height: 16px;
  border: 2px solid transparent;
  border-top: 2px solid white;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.mode-switch {
  text-align: center;
  margin-top: 20px;
  padding: 15px;
}

.mode-switch p {
  margin: 0;
  font-size: 14px;
  color: #666;
}

.switch-button {
  background: none;
  border: none;
  color: #667eea;
  font-weight: 600;
  cursor: pointer;
  text-decoration: underline;
  font-size: 14px;
  padding: 0;
  margin-left: 5px;
}

.switch-button:hover:not(:disabled) {
  color: #5a67d8;
}

.switch-button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.demo-credentials {
  background-color: #f8f9fa;
  border-radius: 10px;
  padding: 15px;
  margin-top: 20px;
  text-align: center;
}

.demo-credentials p {
  margin: 5px 0;
  font-size: 14px;
  color: #666;
}

.demo-credentials strong {
  color: #333;
}

@media (max-width: 480px) {
  .login-card {
    padding: 30px 20px;
  }
  
  .login-header h1 {
    font-size: 24px;
  }
}
</style> 