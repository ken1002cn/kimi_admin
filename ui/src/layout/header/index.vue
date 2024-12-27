<template>
  <header class="header">
    <div class="logo">
      <img src="@/assets/logo.png" alt="Logo" class="logo-img" />
    </div>
    
    <div class="site-name">
      <h1>基础管理后台</h1>
    </div>

    <div class="user-info">
      <el-dropdown v-if="username">
        <span class="el-dropdown-link" style="color:aqua;">
          欢迎，{{ username }}
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="toLoginPage">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <a v-else @click="toLoginPage">登录</a>
    </div>
  </header>
</template>

<script>
import router from '@/router';

export default {
  name: "Header",
  data() {
    return {
      username: ""
    };
  },
  created() {
    // 从 sessionStorage 获取用户名
    const username = window.sessionStorage.getItem("username");
    this.username = username || "";
  },
  methods: {
    toLoginPage() {
      window.sessionStorage.removeItem("token");
      window.sessionStorage.removeItem("username");
      window.sessionStorage.removeItem("uid");
      router.replace("/login");
    }
  }
};
</script>

<style scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 20px;
  background-color: #333;
  color: white;
}

.logo {
  display: flex;
  align-items: center;
}

.logo-img {
  height: 40px; /* 设置 logo 高度 */
  margin-right: 10px; /* Logo 和网站名称之间的间距 */
}

.site-name h1 {
  font-size: 24px;
  font-weight: bold;
  margin: 0;
}

.user-info {
  font-size: 16px;
}
</style>
