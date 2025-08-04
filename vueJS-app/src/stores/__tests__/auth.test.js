import { describe, it, expect, beforeEach, vi } from 'vitest'
import { setActivePinia, createPinia } from 'pinia'
import { useAuthStore } from '../auth'

// Mock localStorage
const localStorageMock = {
  getItem: vi.fn(),
  setItem: vi.fn(),
  removeItem: vi.fn(),
  clear: vi.fn()
}
Object.defineProperty(window, 'localStorage', {
  value: localStorageMock,
  writable: true,
})

describe('Auth Store', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
    vi.clearAllMocks()
  })

  describe('State', () => {
    it('should have initial state', () => {
      const store = useAuthStore()
      expect(store.user).toBe(null)
      expect(store.token).toBe(null)
      expect(store.isLoading).toBe(false)
      expect(store.error).toBe(null)
    })
  })

  describe('Getters', () => {
    it('should return true for isAuthenticated when token exists', () => {
      const store = useAuthStore()
      store.token = 'test-token'
      expect(store.isAuthenticated).toBe(true)
    })

    it('should return false for isAuthenticated when token is null', () => {
      const store = useAuthStore()
      store.token = null
      expect(store.isAuthenticated).toBe(false)
    })

    it('should return current user', () => {
      const store = useAuthStore()
      const mockUser = { id: 1, username: 'test' }
      store.user = mockUser
      expect(store.currentUser).toEqual(mockUser)
    })
  })

  describe('Actions', () => {
    describe('logout', () => {
      it('should clear user data and localStorage', () => {
        const store = useAuthStore()
        store.user = { id: 1, username: 'test' }
        store.token = 'test-token'
        
        store.logout()
        
        expect(store.user).toBe(null)
        expect(store.token).toBe(null)
        expect(localStorageMock.removeItem).toHaveBeenCalledWith('token')
        expect(localStorageMock.removeItem).toHaveBeenCalledWith('user')
      })
    })
  })
}) 