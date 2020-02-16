import request from './request'

export function login (data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getUserInfo () {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function getUserList (currentPage, pageSize) {
  return request({
    url: '/user/list',
    method: 'get',
    params: {
      currentPage: currentPage,
      pageSize: pageSize
    }
  })
}
export function deleteUser (id) {
  return request({
    url: `/user/${id}`,
    method: 'delete'
  })
}

export function getArticle (id) {
  return request({
    url: `/article/${id}`,
    method: 'get'
  })
}

export function updateArticle (id, data) {
  return request({
    url: `/article/${id}`,
    method: 'put',
    data
  })
}

export function addArticle (data) {
  return request({
    url: '/article/',
    method: 'post',
    data
  })
}

export function deleteArticle (id) {
  return request({
    url: `/article/${id}`,
    method: 'delete'
  })
}

export function getArticleList (currentPage, pageSize) {
  return request({
    url: '/article/list',
    method: 'get',
    params: {
      currentPage: currentPage,
      pageSize: pageSize
    }
  })
}

export function batchDeleteArticle (data) {
  return request({
    url: '/articles',
    method: 'delete',
    data
  })
}

export function getAllCategory () {
  return request({
    url: '/categories',
    method: 'get'
  })
}

export function addCategory (data) {
  return request({
    url: '/category',
    method: 'post',
    data
  })
}

export function deleteCategory (id) {
  return request({
    url: `/category/${id}`,
    method: 'delete'
  })
}

export function updateCategory (id, data) {
  return request({
    url: `/category/${id}`,
    method: 'put',
    data
  })
}

export function batchDeleteCategory (data) {
  return request({
    url: '/categories',
    method: 'delete',
    data
  })
}
export function getAllSubCategory () {
  return request({
    url: '/subCategories',
    method: 'get'
  })
}

export function getAllTag () {
  return request({
    url: '/tags',
    method: 'get'
  })
}
