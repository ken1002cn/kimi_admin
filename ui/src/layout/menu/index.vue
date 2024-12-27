<template>
 <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="2"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
      >
      <el-menu-item index="1" disabled>
          <el-icon><document /></el-icon>
          <span>功能列表</span>
        </el-menu-item>

        <el-menu-item index="2" @click="toUser">
          <el-icon><icon-menu /></el-icon>
          <span>用户管理</span>
        </el-menu-item>

        <el-menu-item index="3" @click="toRole">
          <el-icon><icon-menu /></el-icon>
          <span>角色管理</span>
        </el-menu-item>
      
        <el-menu-item index="4" @click="toDept">
          <el-icon><setting /></el-icon>
          <span>部门管理</span>
        </el-menu-item>

        <el-menu-item index="5" @click="toAdvice">
          <el-icon><setting /></el-icon>
          <span>问题反馈</span>
        </el-menu-item>


      </el-menu>
</template>
 
<script>
import router from '@/router';

export default {
  name: "Header",
  data() {
    return {
     
    };
  },
  created() {
   
  },
  methods: {
    toUser(){
      router.replace("/");
    },
    toRole(){
      router.replace("/role");
    },
    toDept(){
      router.replace("/dept");
    },
    toAdvice(){
      router.replace("/advice");
    }

  }
};
</script>
 
<style lang="scss" scoped>
 
</style>
 