import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from './router'
import App from './App.vue'
import './style.css'
import { validateConfig, config } from './config/env'

// Validar configuración al inicio
validateConfig()

// Establecer el título de la página dinámicamente
document.title = config.APP_TITLE

const app = createApp(App)
const pinia = createPinia()

app.use(pinia)
app.use(router)
app.mount('#app') 