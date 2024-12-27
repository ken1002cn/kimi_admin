<template>
    <!-- 操作栏 -->
    <div style="height: 50px;">
        <el-button type="primary" @click="openCreateDialogue()">新增角色</el-button>
    </div>
<!--  表格 -->
   <div>
      <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="roleName" label="角色名" width="150" />
    <el-table-column prop="code" label="角色编码" width="120" />
    <el-table-column prop="deptName" label="所属部门" width="120" />
    <el-table-column prop="createTime" label="创建时间" width="120" />
    <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="{ row }">
        <el-button link type="primary" size="small" @click="handelDel(row.id)">删除</el-button>
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
      @current-change="handlePageChange"
    />
    <!-- 添加权限弹框 -->
    <el-dialog
    v-model="create_Role_dialogVisible"
    title="Tips"
    width="600px"
    :before-close="handleClose"
  >
         <!-- 用户信息表单 -->
         <el-form :model="create_form" label-width="auto" style="max-width: 600px">
            <el-form-item label="角色名">
                <el-input v-model="create_form.roleName" />
            </el-form-item>

            <el-form-item label="角色编码">
                <el-input v-model="create_form.code" />
            </el-form-item>

            <el-form-item label="所属部门">
                <el-select
                    v-model="create_form.deptId"
                    placeholder="Select"
                    size="large"
                    style="width: 240px"
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
        <el-button @click="create_Role_dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="postRoleObj()">
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
      tableData: [],
      currentPage:1,
      pageSize:12,
      total:0,
      create_Role_dialogVisible : false,
      create_form:{},
      depts:[],//公司集合
    };
  },
  methods: {
    async handelDel(id){
      try{
        await request.get(`api/role/delete/${id}`)
        ElMessage({
        message: '角色删除成功',
        type: 'success',
        })
        this.fetchDate()
      }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    },
    async fetchDate(page = 1){
        try{
            let result = await request.post("api/role/list",{
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
    handlePageChange(page){
        this.currentPage = page;
        this.fetchDate(page)
    },
    async openCreateDialogue(){
      this.create_Role_dialogVisible = true
     try{
      let result = await request.post("api/dept/list",{
        pageNum : 1,
        pageSize : 1000
      })
      this.depts = result.data.data.records
     }catch(error){
      ElMessage({
            message: error,
            type: 'warning',
        })
     }
    },
    async postRoleObj(){
      try{
        await request.post("api/role/create",this.create_form)
        ElMessage({
        message: '角色添加成功',
        type: 'success',
        })
        this.fetchDate()
        this.create_Role_dialogVisible = false
        this.create_form = {}
      }catch(error){
            ElMessage({
            message: error,
            type: 'warning',
        })
        }
    }
  },
  mounted(){
    this.fetchDate();
  }
};
</script>