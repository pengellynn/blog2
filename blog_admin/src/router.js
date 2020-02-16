import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import ArticleList from './components/ArticleList'
import CreateArticle from './components/CreateArticle'
import EditArticle from './components/EditArticle'
import User from './components/User'
import Column from './components/Column'
import Statistic from './components/Statistic'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: '登录',
      component: Login,
      hidden: true
    },
    {
      path: '/',
      name: '首页',
      component: Home,
      redirect: '/article/list',
      hidden: true
    },
    {
      path: '/article',
      name: '文章管理',
      component: Home,
      redirect: '/article/list',
      meta: { icon: 'el-icon-document' },
      children: [
        {
          path: 'list',
          name: '文章列表',
          component: ArticleList,
          meta: { title: '文章列表', icon: 'el-icon-s-fold' }
        },
        {
          path: 'create',
          name: '创建文章',
          component: CreateArticle,
          meta: { title: '创建文章', icon: 'el-icon-edit-outline' }
        },
        {
          path: 'edit/:id',
          name: '编辑文章',
          component: EditArticle,
          hidden: true
        }
      ]
    },
    {
      path: '/columns',
      component: Home,
      children: [
        {
          path: '',
          name: '栏目管理',
          component: Column,
          meta: { title: '栏目管理', icon: 'el-icon-menu' }
        }
      ]
    },
    {
      path: '/users',
      component: Home,
      children: [
        {
          path: '',
          name: '用户管理',
          component: User,
          meta: { title: '用户管理', icon: 'el-icon-user-solid' }
        }
      ]
    },
    {
      path: '/statistics',
      component: Home,
      children: [
        {
          path: '',
          name: '数据统计',
          component: Statistic,
          meta: { title: '数据统计', icon: 'el-icon-s-data' }
        }
      ]
    }

  ]
})
