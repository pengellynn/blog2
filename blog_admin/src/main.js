import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

Vue.use(ElementUI)
Vue.use(MavonEditor)
Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (store.state.user.token) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      if (store.state.user.roles.length === 0) {
        store.dispatch('user/getUserInfo')
          .then(() => {
            next()
            // const roles = res.data.role
            // store.dispatch('generateRoutes', { roles })
            //   .then(() => {
            //     router.addRoutes(store.getters.addRoutes)
            //     next({ ...to, replace: true })
            //   })
          })
          .catch(error => {
            console.log(error)
          })
      } else {
        next()
      }
    }
  } else {
    if (to.path === '/login') {
      next()
    } else {
      next({ path: '/login' })
    }
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
