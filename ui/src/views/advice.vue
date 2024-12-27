<template>
    <div class="feedback-page">
      <el-card>
        <h2>用户反馈</h2>
  
        <!-- 反馈信息表单 -->
        <el-form ref="form" :model="form" label-width="auto" style="max-width: 600px; margin-top: 20px;">
          
          <!-- 用户姓名 -->
          <el-form-item label="姓名" prop="name" :rules="[{ required: true, message: '请输入您的姓名', trigger: 'blur' }]">
            <el-input v-model="form.name" placeholder="请输入姓名" />
          </el-form-item>
  
          <!-- 用户邮箱 -->
          <el-form-item label="邮箱" prop="email" :rules="[{ required: true, message: '请输入您的邮箱', trigger: 'blur' }, { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }]">
            <el-input v-model="form.email" placeholder="请输入邮箱" />
          </el-form-item>
  
          <!-- 反馈内容 -->
          <el-form-item label="反馈内容" prop="advice" :rules="[{ required: true, message: '请输入反馈内容', trigger: 'blur' }]">
            <el-input type="textarea" v-model="form.advice" placeholder="请输入您的反馈" rows="4" />
          </el-form-item>
  
          <!-- 用户满意度 -->
          <el-form-item label="满意度" prop="satisfaction" :rules="[{ required: true, message: '请选择满意度', trigger: 'change' }]">
            <el-rate v-model="form.satisfaction" />
          </el-form-item>
  
          <!-- 反馈原因 -->
          <el-form-item label="反馈原因" prop="feedbackReasons">
            <el-checkbox-group v-model="form.feedbackReasons">
              <el-checkbox label="产品质量" name="feedback-reasons">产品质量</el-checkbox>
              <el-checkbox label="服务态度" name="feedback-reasons">服务态度</el-checkbox>
              <el-checkbox label="价格问题" name="feedback-reasons">价格问题</el-checkbox>
              <el-checkbox label="功能不全" name="feedback-reasons">功能不全</el-checkbox>
              <el-checkbox label="其他" name="feedback-reasons">其他</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
  
          <!-- 提交按钮 -->
          <el-form-item>
            <el-button type="primary" @click="submitFeedback">提交反馈</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  import request from '@/util/request'; // 导入请求工具类
  import { ElMessage } from 'element-plus'; // 导入 Element Plus 消息提示
  
  export default {
    data() {
      return {
        form: {
          name: '',
          email: '',
          advice: '',
          satisfaction: 3, // 默认为3分（中等满意度）
          feedbackReasons: [] // 用于存储复选框的选择
        }
      };
    },
    methods: {
      // 提交用户反馈
      submitFeedback() {
        // 校验表单数据
        this.$refs.form.validate((valid) => {
          if (valid) {
            // 如果表单验证通过，发送请求
            request.post('api/advice/commit', this.form)
              .then((response) => {
                ElMessage.success('反馈提交成功！');
                // 提交后重置表单
                this.$refs.form.resetFields();
              })
              .catch((error) => {
                ElMessage.error('提交失败，请稍后再试！');
              });
          } else {
            ElMessage.error('请填写完整的反馈信息！');
            return false;
          }
        });
      }
    }
  };
  </script>
  