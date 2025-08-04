import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import Dashboard from '../Dashboard.vue'

describe('Dashboard Component', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
    vi.clearAllMocks()
  })

  describe('Rendering', () => {
    it('should render dashboard', () => {
      const wrapper = mount(Dashboard)
      expect(wrapper.find('.dashboard').exists()).toBe(true)
    })

    it('should have logout button', () => {
      const wrapper = mount(Dashboard)
      expect(wrapper.find('button').exists()).toBe(true)
    })

    it('should display welcome message', () => {
      const wrapper = mount(Dashboard)
      expect(wrapper.text()).toContain('Bienvenido')
    })
  })

  describe('User Actions', () => {
    it('should have logout functionality', () => {
      const wrapper = mount(Dashboard)
      const logoutButton = wrapper.find('button')
      expect(logoutButton.exists()).toBe(true)
    })
  })
}) 