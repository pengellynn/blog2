const state = {
  httpError: {
    status: '',
    statusText: ''
  }
}

const mutations = {
  SET_HTTP_ERROR (state, httpError) {
    state.httpError = httpError
  }
}

const actions = {
  setHttpError ({ commit }, httpError) {
    commit('SET_HTTP_ERROR', httpError)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
