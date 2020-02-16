import axios from 'axios'
import store from '@/store'

const service = axios.create({
  baseURL: 'http://localhost:8080',
  // baseURL: 'https://easy-mock.com/mock/5d00c05a1c7f40720e804ade/example',
  timeout: 5000
})

service.interceptors.request.use(config => {
  if (store.state.user.token) {
    config.headers.Authorization = `Bearer ${store.state.user.token}`
  }
  return config
}, error => {
  console.log(error)
  return Promise.reject(error)
})

service.interceptors.response.use(response => {
  return Promise.resolve(response)
}, error => {
  const httpError = {
    status: error.response.status,
    statusText: error.response.statusText
  }
  store.dispatch('http/setHttpError', httpError).then(() => {
    return Promise.reject(error)
  })
})

export default service
