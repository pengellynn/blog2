<template>
  <div class="home">
    <el-container>
      <el-header>
        <div class="home-title">博客管理平台</div>
        <div>
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link user-info">
              {{currentUsername}}<i class="el-icon-arrow-down el-icon--right user-info"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="myArticle">我的文章</el-dropdown-item>
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside>
          <el-menu
            default-active="this.$router.currentRoute.path"
            router>
            <template v-for="(item, index) in routes">
              <el-submenu
                v-if="item.children.length>1"
                :index="item.path"
                :key="index"
              >
                <template slot="title">
                  <i :class="item.meta.icon"></i>
                  <span>{{item.name}}</span>
                </template>
                <el-menu-item
                  v-for="child in children(item)"
                  :index="resolvePath(item,child)"
                  :key="child.path"
                >
                  <i :class="child.meta.icon"></i>
                  <span slot="title">{{child.meta.title}}</span>
                </el-menu-item>
              </el-submenu>
              <template v-if="item.children.length===1">
                <el-menu-item
                  :index="resolvePath(item,item.children[0])"
                  :key="item.path"
                >
                  <i :class="item.children[0].meta.icon"></i>
                  <span slot="title">{{item.children[0].name}}</span>
                </el-menu-item>
              </template>
            </template>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <div class="view-container">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'home',
  computed: {
    currentUsername () {
      return this.$store.state.user.name
    },
    routes () {
      return this.$router.options.routes.filter(item => !item.hidden)
    }
  },
  methods: {
    handleCommand (command) {
      if (command === 'myArticle') {
        this.$router.push({ path: '/article/list' })
      } else if (command === 'logout') {
        this.$confirm('确认退出登录', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            this.$store.dispatch('user/logout')
              .then(() => {
                this.$router.replace({ path: '/login' })
              })
          })
      }
    },
    resolvePath (item, child) {
      return item.path + '/' + child.path
    },
    children (item) {
      return item.children.filter(child => !child.hidden)
    }
  }
}
</script>

<style>
  body {
    margin: 0;
    padding: 0;
  }
  .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .home-title {
    font-size: 22px;
    color: white;
    display: inline;
  }
  .user-info {
    color: white;
    cursor: pointer;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  .view-container {
    margin-top: 30px;
    padding: 0 20px;
  }
</style>
