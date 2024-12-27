<template>
  <div class="login">

    <el-form ref="loginRef" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">基础管理后台</h3>

      <el-form-item prop="account">

        <el-input
            v-model="loginForm.account"
            type="text"
            size="large"
            auto-complete="off"
            placeholder="账号"
        >

        </el-input>
      </el-form-item>
      
      <el-form-item prop="password">
        <el-input
            v-model="loginForm.password"
            type="password"
            size="large"
            auto-complete="off"
            placeholder="密码"
        >

        </el-input>
      </el-form-item>
<!-- 登录按钮 -->
      <el-form-item style="width:100%;">
        <el-button
            size="large"
            type="primary"
            style="width:100%;"
            @click.prevent="handleLogin"
        >
          <span>登 录</span>

        </el-button>
      </el-form-item>
<!-- 注册按钮 -->
      <!-- <el-form-item style="width:100%;">
        <el-button
            size="large"
            type="primary"
            style="width:100%;"
            @click.prevent="handleRegister"
        >
          <span>注册</span>

        </el-button>
      </el-form-item> -->
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <!-- <span>Copyright © 2024-2025 <a href="#" target="_blank">名称占位</a> 版权所有.</span> -->
    </div>
  </div>
</template>

<script setup>
  import request from '@/util/request';
  import {ref} from 'vue'
  import { ElMessage } from 'element-plus'
  import router from '@/router';
  const loginForm = ref({
    account:'',
    password:''
  })

  const loginRef = ref(null)

  const loginRules = {
    username: [{required:true,trigger:"blur",message:"请输入您的账号"}],
    password: [{required:true,trigger:"blur",message:"请输入您的密码"}]
  }
  const handleLogin = ()=>{
    loginRef.value.validate(async (valid)=>{
      if(valid){
        let result = await request.post("api/user/login",loginForm.value)
        let data = result.data
        if(data.code==200){
          ElMessage({
          message: '登陆成功!',
          type: 'success',
        })
          window.sessionStorage.setItem("token",data.data.token)
          window.sessionStorage.setItem("username",data.data.username) 
          window.sessionStorage.setItem("uid",data.data.uid)
          router.replace("/")
        }else{
          ElMessage({
          message: data.msg,
          type: 'warning',
  })
        }
        console.log(result)
      }else{ //账号密码输入栏校验未通过
        ElMessage({
        message: '请输入账号密码',
        type: 'warning',
  })
      }
    })
  }
  const handleRegister = ()=>{
    router.replace("/register")
  }
</script>

<style lang="scss" scoped>
/* 页面整体布局，确保登录框居中 */
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;  /* 视口高度，保证页面满屏 */
  background: url('../assets/images/Login.png') no-repeat center center; /* 背景图居中 */
  background-size: cover; /* 背景图铺满整个屏幕 */
}

/* 登录表单样式 */
.login-form {
  background-color: rgba(255, 255, 255, 0.8); /* 背景颜色，带透明度，避免完全透明影响可读性 */
  padding: 40px;
  width: 400px; /* 自定义登录框宽度 */
  border-radius: 10px; /* 圆角 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* 阴影 */
}

/* 标题样式 */
.title {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

/* 输入框和按钮样式调整 */
.el-form-item {
  margin-bottom: 20px;
}

/* 记住密码样式 */
.el-checkbox {
  display: block;
  margin-bottom: 25px;
  text-align: center;
}

/* 登录按钮样式 */
.el-button {
  width: 100%;
  background-color: #409EFF;
  color: white;
  border-radius: 5px;
  font-size: 16px;
}

/* 页脚样式 */
.el-login-footer {
  text-align: center;
  font-size: 14px;
  color: #999;
  margin-top: 30px;
}

.el-login-footer a {
  color: #409EFF;
  text-decoration: none;
}

.el-login-footer a:hover {
  text-decoration: underline;
}
</style>
