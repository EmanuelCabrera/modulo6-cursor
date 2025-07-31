<template>
  <div class="dashboard">
    <header class="dashboard-header">
      <div class="header-content">
        <h1>Dashboard</h1>
        <div class="user-info">
          <span>Bienvenido, {{ authStore.currentUser?.fullName || authStore.currentUser?.username }}</span>
          <button @click="handleLogout" class="logout-button">
            Cerrar Sesión
          </button>
        </div>
      </div>
    </header>
    
    <main class="dashboard-content">
      <div class="welcome-card">
        <h2>¡Inicio de sesión exitoso!</h2>
        <p>Has iniciado sesión correctamente en la aplicación.</p>
        
        <div class="user-details">
          <h3>Información del usuario:</h3>
          <ul>
            <li><strong>ID:</strong> {{ authStore.currentUser?.id }}</li>
            <li><strong>Username:</strong> {{ authStore.currentUser?.username }}</li>
            <li><strong>Email:</strong> {{ authStore.currentUser?.email }}</li>
            <li><strong>Nombre completo:</strong> {{ authStore.currentUser?.fullName }}</li>
            <li><strong>Token:</strong> {{ authStore.token?.substring(0, 20) }}...</li>
          </ul>
        </div>
        
        <div class="actions">
          <button @click="loadUsers" class="action-button">
            Cargar Usuarios
          </button>
          <button @click="showUserInfo" class="action-button">
            Ver Información Completa
          </button>
        </div>
      </div>
      
      <div v-if="users.length > 0" class="users-section">
        <h2>Lista de Usuarios</h2>
        <div class="users-grid">
          <div v-for="user in users" :key="user.id" class="user-card">
            <div class="user-avatar">
              {{ user.username?.charAt(0).toUpperCase() || 'U' }}
            </div>
            <div class="user-info">
              <h3>{{ user.fullName || user.username }}</h3>
              <p class="user-email">{{ user.email }}</p>
              <p class="user-status" :class="{ active: user.isActive }">
                {{ user.isActive ? 'Activo' : 'Inactivo' }}
              </p>
            </div>
          </div>
        </div>
      </div>
      
      <div v-if="loading" class="loading-section">
        <div class="loading-spinner"></div>
        <p>Cargando usuarios...</p>
      </div>
      
      <div v-if="error" class="error-section">
        <p class="error-message">{{ error }}</p>
      </div>
      
      <div class="features-grid">
        <div class="feature-card">
          <h3>🔐 Autenticación</h3>
          <p>Sistema de login funcional con validación de credenciales</p>
        </div>
        <div class="feature-card">
          <h3>🛡️ Protección de Rutas</h3>
          <p>Navegación protegida con guards de autenticación</p>
        </div>
        <div class="feature-card">
          <h3>💾 Persistencia</h3>
          <p>Estado de autenticación persistente en localStorage</p>
        </div>
        <div class="feature-card">
          <h3>🎨 UI Moderna</h3>
          <p>Interfaz moderna y responsive con animaciones</p>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'
import { userService } from '../services/api'

export default {
  name: 'Dashboard',
  setup() {
    const router = useRouter()
    const authStore = useAuthStore()
    const users = ref([])
    const loading = ref(false)
    const error = ref(null)
    
    const handleLogout = () => {
      authStore.logout()
      router.push('/login')
    }
    
    const loadUsers = async () => {
      loading.value = true
      error.value = null
      
      try {
        const response = await userService.getAllUsers()
        users.value = response
      } catch (err) {
        error.value = err.response?.data?.message || 'Error al cargar usuarios'
        console.error('Error loading users:', err)
      } finally {
        loading.value = false
      }
    }
    
    const showUserInfo = () => {
      alert(JSON.stringify(authStore.currentUser, null, 2))
    }
    
    // Cargar usuarios al montar el componente
    onMounted(() => {
      loadUsers()
    })
    
    return {
      authStore,
      users,
      loading,
      error,
      handleLogout,
      loadUsers,
      showUserInfo
    }
  }
}
</script>

<style scoped>
.dashboard {
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
}

.dashboard-header {
  background: white;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px 0;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-content h1 {
  margin: 0;
  color: #333;
  font-size: 24px;
  font-weight: 600;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 15px;
  margin-left: auto;
  justify-content: flex-end;
}

.user-info span {
  color: #666;
  font-size: 14px;
  font-weight: 500;
}

.logout-button {
  background: #dc3545;
  color: white;
  border: none;
  padding: 10px 18px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.3s ease;
  box-shadow: 0 2px 4px rgba(220, 53, 69, 0.2);
}

.logout-button:hover {
  background: #c82333;
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(220, 53, 69, 0.3);
}

.dashboard-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 20px;
}

.welcome-card {
  background: white;
  border-radius: 15px;
  padding: 30px;
  margin-bottom: 40px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
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

.welcome-card h2 {
  color: #333;
  margin: 0 0 15px 0;
  font-size: 28px;
  font-weight: 600;
}

.welcome-card p {
  color: #666;
  margin: 0 0 25px 0;
  font-size: 16px;
}

.user-details {
  background: #f8f9fa;
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 25px;
}

.user-details h3 {
  color: #333;
  margin: 0 0 15px 0;
  font-size: 18px;
  font-weight: 600;
}

.user-details ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.user-details li {
  padding: 8px 0;
  border-bottom: 1px solid #e9ecef;
  color: #666;
  font-size: 14px;
}

.user-details li:last-child {
  border-bottom: none;
}

.user-details strong {
  color: #333;
  font-weight: 600;
}

.actions {
  display: flex;
  gap: 15px;
  flex-wrap: wrap;
}

.action-button {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  padding: 12px 20px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.3s ease;
}

.action-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.3);
}

.users-section {
  margin-bottom: 40px;
}

.users-section h2 {
  color: #333;
  margin: 0 0 20px 0;
  font-size: 24px;
  font-weight: 600;
}

.users-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.user-card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
  display: flex;
  align-items: center;
  gap: 15px;
  transition: transform 0.3s ease;
}

.user-card:hover {
  transform: translateY(-2px);
}

.user-avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 600;
}

.user-info {
  flex: 1;
}

.user-info h3 {
  margin: 0 0 5px 0;
  color: #333;
  font-size: 16px;
  font-weight: 600;
}

.user-email {
  margin: 0 0 5px 0;
  color: #666;
  font-size: 14px;
}

.user-status {
  margin: 0;
  font-size: 12px;
  font-weight: 500;
  padding: 2px 8px;
  border-radius: 12px;
  display: inline-block;
  background: #f8f9fa;
  color: #666;
}

.user-status.active {
  background: #d4edda;
  color: #155724;
}

.loading-section {
  text-align: center;
  padding: 40px;
}

.loading-section .loading-spinner {
  width: 40px;
  height: 40px;
  border: 4px solid #f3f3f3;
  border-top: 4px solid #667eea;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin: 0 auto 20px;
}

.error-section {
  background: #fee;
  border: 1px solid #fecaca;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 20px;
}

.error-message {
  color: #c53030;
  margin: 0;
  font-size: 14px;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.feature-card {
  background: white;
  border-radius: 12px;
  padding: 25px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
  transition: transform 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-5px);
}

.feature-card h3 {
  color: #333;
  margin: 0 0 15px 0;
  font-size: 18px;
  font-weight: 600;
}

.feature-card p {
  color: #666;
  margin: 0;
  font-size: 14px;
  line-height: 1.5;
}

@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    gap: 15px;
    text-align: center;
  }
  
  .user-info {
    flex-direction: column;
    gap: 10px;
    margin-left: 0;
  }
  
  .logout-button {
    padding: 12px 24px;
    font-size: 16px;
  }
  
  .actions {
    justify-content: center;
  }
  
  .features-grid {
    grid-template-columns: 1fr;
  }
}
</style> 