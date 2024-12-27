<template>
    <!-- 操作栏 -->
    <div style="height: 50px;">
        <el-button type="primary" @click="create_User_dialogVisible = true">创建用户</el-button>
    </div>
    <!--页面数据表单 -->
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column fixed prop="account" label="账号" width="150" />
        <el-table-column prop="nickname" label="昵称" width="120" />
        <el-table-column prop="deptName" label="部门" width="120" />
        <el-table-column prop="password" label="密码" width="120" />
        <el-table-column prop="email" label="电子邮箱" width="120" />
        <el-table-column prop="sex" label="性别" width="120" />
        <el-table-column prop="phone" label="电话号码" width="120" />
        <el-table-column prop="status" label="状态" width="120" />
        <el-table-column prop="createTime" label="注册时间" width="120" />
        <el-table-column prop="roles" label="权限" width="150" />
        <!-- 操作栏 -->
        <el-table-column fixed="right" label="操作" min-width="120">
            <template #default="{ row }">
            <!-- 根据状态显示不同按钮 -->
            <el-button 
              v-if="row.status === '启用'" 
              type="danger" 
              size="small" 
              @click="handleStatusToggle(row.id)">
              停用
            </el-button>
            <el-button 
              v-else 
              type="primary" 
              size="small" 
              @click="handleStatusToggle(row.id)">
              启用
            </el-button>
            <el-button type="primary" size="small" @click="modifyUser(row)">编辑</el-button>
            <el-button type="primary" size="small" @click="openRoleDialogue(row)">授权</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="currentPage"
      :page-size="pageSize"
      :total="total"
      layout="total, prev, pager, next, jumper"
      @current-change="handlePageChange"/>

    <!-- 添加用户弹框 -->
    <el-dialog
      v-model="create_User_dialogVisible"
      title="Tips"
      width="600px"
      :before-close="handleClose">
         <!-- 用户信息表单 -->
         <el-form :model="create_form" label-width="auto" style="max-width: 600px">
            <el-form-item label="账号">
                <el-input v-model="create_form.account" />
            </el-form-item>

            <el-form-item label="用户名">
                <el-input v-model="create_form.nickname" />
            </el-form-item>

            <el-form-item label="密码">
                <el-input v-model="create_form.password" />
            </el-form-item>

            <el-form-item label="确认密码">
                <el-input v-model="create_form.password_check" />
            </el-form-item>

            <el-form-item label="邮箱">
                <el-input v-model="create_form.email" />
            </el-form-item>

            <el-form-item label="手机号码">
                <el-input v-model="create_form.phone" />
            </el-form-item>
            
            <el-form-item label="性别">
                <el-select
                    v-model="create_form.sex"
                    placeholder="Select"
                    size="large"
                    style="width: 240px"
                    >
                    <el-option
                        v-for="item in sex"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
        </el-form-item>

        <el-form-item label="权限">
          <el-select
            v-model="create_form.roles"
            multiple
            collapse-tags
            placeholder="Select"
            style="width: 240px"
          >
            <el-option
              v-for="item in roles"
              :key="item.id"
              :label="item.roleName"
              :value="item.id"
            />
    </el-select>
        </el-form-item>

        <el-form-item label="公司">
                <el-select
                    v-model="create_form.dept"
                    placeholder="Select"
                    size="large"
                    style="width: 240px"
                    @change="fetchRoleData"
                    >
                    <el-option
                        v-for="item in depts"
                        :key="item.id"
                        :label="item.deptName"
                        :value="item.id"
                    />
                </el-select>
        </el-form-item>

        <el-form-item label="启用/停用">
                    <el-switch v-model="create_form.status" />
        </el-form-item>
  </el-form>
<!-- 底部按钮区 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="postUserObj()">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>

 <!-- 修改用户弹框 -->
 <el-dialog
    v-model="modify_User_dialogVisible"
    title="Tips"
    width="600px"
    :before-close="handleClose">
         <!-- 用户信息表单 -->
            <el-form :model="modify_form" label-width="auto" style="max-width: 600px">
        
              <el-form-item label="用户名">
                  <el-input v-model="modify_form.nickname" />
              </el-form-item>

              <el-form-item label="邮箱">
                  <el-input v-model="modify_form.email" />
              </el-form-item>

              <el-form-item label="密码">
                  <el-input v-model="modify_form.password" />
              </el-form-item>

              <el-form-item label="手机号码">
                  <el-input v-model="modify_form.phone" />
              </el-form-item>
              
              <el-form-item label="性别">
                  <el-select
                      v-model="modify_form.sex"
                      placeholder="Select"
                      size="large"
                      style="width: 240px"
                      >
                      <el-option
                          v-for="item in sex"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                      />
                  </el-select>
              </el-form-item>
            </el-form>
<!-- 底部按钮区 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="modify_User_dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateUserObj()">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>

<!-- 修改权限弹框 -->
<el-dialog
    v-model="modify_role_dialogVisible"
    title="Tips"
    width="600px"
    :before-close="handleClose">
         <!-- 权限表单 -->
         <el-form :model="role_form" label-width="auto" style="max-width: 600px">
          <el-form-item label="权限">
          <el-select
            v-model="role_form.roles"
            multiple
            collapse-tags
            placeholder="Select"
            style="width: 240px"
          >
            <el-option
              v-for="item in roles"
              :key="item.id"
              :label="item.roleName"
              :value="item.id"
            />
    </el-select>
        </el-form-item>
        <el-form-item label="公司">
                <el-select
                    v-model="role_form.dept"
                    placeholder="Select"
                    size="large"
                    style="width: 240px"
                    @change="fetchRoleData"
                    >
                    <el-option
                        v-for="item in depts"
                        :key="item.id"
                        :label="item.deptName"
                        :value="item.id"
                    />
                </el-select>
        </el-form-item>
  </el-form>
<!-- 底部按钮区 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="modify_role_dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="modifyUserRole()">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>
  
<script>
import request from '@/util/request';
import { ElMessage } from 'element-plus'
export default {
  data() {
    return {
      // 示例数据
      tableData: [], //页面数据
      currentPage:1,
      pageSize:12,
      total:0,
      create_User_dialogVisible : false,
      modify_User_dialogVisible : false,
      modify_role_dialogVisible : false,
      role_form:{}, //权限更改对象
      create_form:{}, //创建用户对象
      modify_form:{}, //修改用户对像
      sex:[
            {
            value: '男',
            label: '男',
        },
        {
            value: '女',
            label: '女',
        },
      ],
      roles:[],//权限集合
      depts:[],//公司集合
      uid:0 //给权限更新用的 避免数据冲突
    };
  },
  methods: {
    // 拿所有部门
    async fetchDeptData(){
      let result = await request.post("api/dept/list",{
        pageNum : 1,
        pageSize : 1000
      })
      this.depts = result.data.data.records
    },
    async fetchRoleData(deptId){
      let result = await request.get(`api/role/listByDeptId/${deptId}`)
      this.roles = result.data.data
    },
    // 切换用户状态
    async handleStatusToggle(id) {
      if(id == 1){
        ElMessage({
            message: "禁止更改超级管理员状态",
            type: 'warning',
        })
        return
      }
        try{
          await request.get(`api/user/status/${id}`)
          ElMessage({
          message: '状态更改成功',
          type: 'success',
          })
          this.fetchTabelData()
        }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    },
    // 拿页面数据
    async fetchTabelData(page = 1){
        try{
            let result = await request.post("api/user/list",{
            pageNum : page,
            pageSize : this.pageSize
        });
        this.tableData = result.data.data.records
        this.total = result.data.data.total
        }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    },
    //更新页码触发
    handlePageChange(page){
        this.currentPage = page;
        this.fetchTabelData(page)
    },
    //发送更新用户请求
    async postUserObj(){
     try{
      await request.post("api/user/create",this.create_form)
        ElMessage({
          message: '用户创建成功',
          type: 'success',
          })
        this.fetchTabelData()
        this.create_form = {}
        this.create_User_dialogVisible = false
     }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    },
    //更新用户
    modifyUser(row){
      this.modify_User_dialogVisible = true
      this.modify_form = { ...row };  // 使用解构赋值将 row 的所有属性赋给 modify_form
      this.modify_form.status = null
      this.modify_form.roles = null
    },
    async updateUserObj(){
      try{
        await request.post("api/user/update",this.modify_form)
        ElMessage({
          message: '用户创建成功',
          type: 'success',
          })
          this.modify_User_dialogVisible = false
          this.fetchTabelData()
        }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    },
    //打开权限修改弹窗
    openRoleDialogue(row){
      this.modify_role_dialogVisible = true
      this.uid = row.id
    },
    //发送更新权限请求
    async modifyUserRole(){
      try{
        this.role_form.id = this.uid
        await request.post("api/user/updateRole",this.role_form)
        ElMessage({
          message: '权限修改成功',
          type: 'success',
          })
          this.modify_role_dialogVisible = false
          this.fetchTabelData()
          this.uid = 0
        }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    }
  },
  mounted(){
    this.fetchTabelData();
    this.fetchDeptData()
  }
};
</script>