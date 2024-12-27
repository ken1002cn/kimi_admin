<template>
    <!-- 操作栏 -->
    <div style="height: 50px;">
        <el-button type="primary" @click="create_dept_dialogVisible = true">创建部门</el-button>
    </div>
<!--  表格 -->
   <div>
      <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="deptName" label="部门名" width="200" />
    <el-table-column prop="parentDept" label="上级部门" width="200" />
    <el-table-column prop="createTime" label="注册时间" width="120" />
    <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="{ row }">
          <el-popconfirm title="注意！一个部门会递归删除其下的子部门以及角色">
            <template #reference>
              <el-button @click="handelDel(row.id)">删除</el-button>
            </template>
          </el-popconfirm>
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
    <!-- 添加部门弹框 -->
    <el-dialog
    v-model="create_dept_dialogVisible"
    title="Tips"
    width="600px"
    :before-close="handleClose"
  >
         <!-- 部门信息表单 -->
         <el-form :model="create_form" label-width="auto" style="max-width: 600px">
            <el-form-item label="部门名">
                <el-input v-model="create_form.deptName" />
            </el-form-item>

            <el-form-item label="上级部门">
                <el-select
                    v-model="create_form.parentId"
                    placeholder="Select"
                    size="large"
                    style="width: 240px"
                    >
                    <el-option
                        v-for="item in tableData"
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
        <el-button @click="create_dept_dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="postNewItem()">
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
      create_dept_dialogVisible : false,
      create_form: {}
    };
  },
  methods: {
    async fetchDate(page = 1){
        try{
            let result = await request.post("api/dept/list",{
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
    async postNewItem(){
    try{
      await request.post("api/dept/create",this.create_form)
      ElMessage({
      message: '部门添加成功',
      type: 'success',
      })
      this.fetchDate()
      this.create_form = {}
      this.create_dept_dialogVisible = false
    }catch(error){
      ElMessage({
            message: error,
            type: 'warning',
        })
    }},
    async handelDel(id){
    try{
      await request.get(`api/dept/delete/${id}`);
      ElMessage({
      message: '部门删除成功',
      type: 'success',
      })
      this.fetchDate();
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