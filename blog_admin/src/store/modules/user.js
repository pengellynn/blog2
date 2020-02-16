import { login, getUserInfo } from '@/utils/api'
import { getToken, setToken, removeToken } from '@/utils/auth'

const state = {
  token: getToken(),
  name: '',
  roles: []
}

const mutations = {
  SET_TOKEN (state, token) {
    state.token = token
  },
  SET_NAME (state, name) {
    state.name = name
  }
}

const actions = {
  loginByUsername ({ commit }, user) {
    const { username, password } = user
    return new Promise((resolve, reject) => {
      login({ username: username, password: password })
        .then(response => {
          const data = response.data.data
          setToken(data.token)
          commit('SET_TOKEN', data.token)
          resolve()
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  getUserInfo ({ commit }) {
    return new Promise((resolve, reject) => {
      getUserInfo()
        .then(response => {
          const data = response.data
          if (!data) {
            reject(new Error('验证失败，请重新登陆'))
          }
          commit('SET_NAME', data.username)
          resolve(data)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  logout ({ commit }) {
    return new Promise((resolve, reject) => {
      try {
        removeToken()
        commit('SET_TOKEN', '')
        resolve()
      } catch (error) {
        reject(error)
      }
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
