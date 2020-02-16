import Vue from 'vue'
import Vuex from 'vuex'

// import http from './modules/http'
import user from './modules/user'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    // http,
    user
  }
})
