import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import Login from '../Login.vue'

describe('Login Component', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
    vi.clearAllMocks()
  })

  describe('Rendering', () => {
    it('should render login form', () => {
      const wrapper = mount(Login)
      expect(wrapper.find('form').exists()).toBe(true)
    })

    it('should have email input', () => {
      const wrapper = mount(Login)
      expect(wrapper.find('input[type="email"]').exists()).toBe(true)
    })

    it('should have password input', () => {
      const wrapper = mount(Login)
      expect(wrapper.find('input[type="password"]').exists()).toBe(true)
    })

    it('should have submit button', () => {
      const wrapper = mount(Login)
      expect(wrapper.find('button[type="submit"]').exists()).toBe(true)
    })
  })

  describe('Form Interaction', () => {
    it('should update form data when inputs change', async () => {
      const wrapper = mount(Login)
      
      const emailInput = wrapper.find('input[type="email"]')
      const passwordInput = wrapper.find('input[type="password"]')
      
      await emailInput.setValue('test@test.com')
      await passwordInput.setValue('password123')
      
      expect(emailInput.element.value).toBe('test@test.com')
      expect(passwordInput.element.value).toBe('password123')
    })
  })

  describe('Validation', () => {
    it('should require email and password fields', () => {
      const wrapper = mount(Login)
      
      const emailInput = wrapper.find('input[type="email"]')
      const passwordInput = wrapper.find('input[type="password"]')
      
      expect(emailInput.attributes('required')).toBeDefined()
      expect(passwordInput.attributes('required')).toBeDefined()
    })
  })
}) 