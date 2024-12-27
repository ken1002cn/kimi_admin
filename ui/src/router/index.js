import { createRouter, createWebHashHistory } from 'vue-router'
import User from '@/views/User.vue'
import Dept from '@/views/Dept.vue'
import Role from '@/views/Role.vue'
import Advice from '@/views/advice.vue'
const routes  = [
  {
    path: '/',
    name: 'home',
    component: () => import('../layout/index.vue'),
    children: [
      {
        path: '', // 默认显示首页
        name: 'user',
        component: User
      },
      {
        path: 'role', 
        name: 'role',
        component: Role
      },
      {
        path: 'dept', 
        name: 'dept',
        component: Dept
      },
      {
        path: 'advice', 
        name: 'advice',
        component: Advice
      }
    ]
  },

  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes 
})

export default router
