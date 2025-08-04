import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import Login from '../../views/Login.vue'
import Dashboard from '../../views/Dashboard.vue'

describe('Authentication Flow Integration', () => {
  let pinia

  beforeEach(() => {
    pinia = createPinia()
    setActivePinia(pinia)
    vi.clearAllMocks()
  })

  describe('Login Component', () => {
    it('should render login form', () => {
      const wrapper = mount(Login)
      expect(wrapper.find('form').exists()).toBe(true)
    })

    it('should have email and password inputs', () => {
      const wrapper = mount(Login)
      expect(wrapper.find('input[type="email"]').exists()).toBe(true)
      expect(wrapper.find('input[type="password"]').exists()).toBe(true)
    })
  })

  describe('Dashboard Component', () => {
    it('should render dashboard', () => {
      const wrapper = mount(Dashboard)
      expect(wrapper.find('.dashboard').exists()).toBe(true)
    })

    it('should display welcome message', () => {
      const wrapper = mount(Dashboard)
      expect(wrapper.text()).toContain('Bienvenido')
    })
  })
}) 