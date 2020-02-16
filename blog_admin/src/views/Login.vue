<template>
 <el-container class="login-container">
   <el-form class="login-from" :rules="rules" :model="loginForm" auto-complete="off" label-position="left">
     <h3 class="login-title">系统登录</h3>
     <el-form-item prop="username">
       <el-input
         type="text"
         v-model="loginForm.username"
         placeholder="用户名"
       />
     </el-form-item>
     <el-form-item prop="password">
       <el-input
         type="password"
         v-model="loginForm.password"
         placeholder="密码"
         @keyup.enter.native="handleLogin"
       />
     </el-form-item>
     <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
     <el-form-item>
       <el-button class="login-button" type="primary" @click="handleLogin">登录</el-button>
     </el-form-item>
  </el-form>
</el-container>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      rules: {
        username: [{ required: true, message: '请输入用户名！', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码！', trigger: 'blur' }]
      },
      loginForm: {
        username: '',
        password: ''
      },
      checked: true
    }
  },
  methods: {
    handleLogin () {
      this.$store.dispatch('user/loginByUsername', this.loginForm).then(() => {
        this.$router.push({ path: '/' })
      }).catch(error => {
        this.$message.error('登录失败')
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    margin: 130px auto;
    width: 30%;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login-from{
    margin: auto;
    width: 80%;
  }

  .login-title {
    margin: 0 auto 30px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0 0 20px 0;
    text-align: left;
  }
  .login-button{
    width: 100%;
  }
</style>
