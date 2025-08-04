import { describe, it, expect, beforeEach, vi } from 'vitest'
import { createRouter, createWebHistory } from 'vue-router'
import { createPinia, setActivePinia } from 'pinia'

describe('Router', () => {
  let router
  let pinia

  beforeEach(() => {
    pinia = createPinia()
    setActivePinia(pinia)
    vi.clearAllMocks()

    // Crear router con rutas básicas
    router = createRouter({
      history: createWebHistory(),
      routes: [
        {
          path: '/',
          redirect: '/login'
        },
        {
          path: '/login',
          name: 'login',
          component: { template: '<div>Login</div>' }
        },
        {
          path: '/dashboard',
          name: 'dashboard',
          component: { template: '<div>Dashboard</div>' }
        }
      ]
    })
  })

  describe('Route Configuration', () => {
    it('should have correct routes', () => {
      expect(router.hasRoute('login')).toBe(true)
      expect(router.hasRoute('dashboard')).toBe(true)
    })

    it('should redirect root to login', async () => {
      await router.push('/')
      expect(router.currentRoute.value.path).toBe('/login')
    })
  })

  describe('Navigation', () => {
    it('should navigate to login', async () => {
      await router.push('/login')
      expect(router.currentRoute.value.path).toBe('/login')
    })

    it('should navigate to dashboard', async () => {
      await router.push('/dashboard')
      expect(router.currentRoute.value.path).toBe('/dashboard')
    })
  })
}) 