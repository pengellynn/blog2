<template>
 <div>
   <el-row type="flex" justify="flex-start" style="flex-wrap: wrap;">
     <el-col :span="8" v-for="(user, index) in userList" :key="index">
       <el-card  :body-style="{ padding: '0 20px' }" class="card">
         <div slot="header" class="clearfix">
           <span>{{user.username}}</span>
           <el-button class="delete-btn" type="text"  icon="el-icon-delete" @click="deleteUser(user.id, index)">删除</el-button>
         </div>
         <div><img src=""></div>
         <div>
           <div class="text-item">
             <span>用户名：</span>
             <span>{{user.username}}</span>
           </div>
           <div class="text-item">
             <span>角色：</span>
             <span>{{user.role}}</span>
           </div>
         </div>
       </el-card>
     </el-col>
   </el-row>
   <div class="pagination">
     <el-pagination
       background
       layout="prev, pager, next, jumper"
       :page-size="pageSize"
       :total="total"
       @current-change="handleCurrentChange"
     />
   </div>
 </div>
</template>

<script>
import { getUserList, deleteUser } from '@/utils/api'
export default {
  name: 'User',
  data () {
    return {
      total: 60,
      pageSize: 6,
      currentPage: 1,
      userList: []
    }
  },
  methods: {
    deleteUser (id, index) {
      deleteUser(id)
        .then(response => {
          if (response.data.status === 'success') {
            this.userList.splice(index, 1)
          }
        })
        .catch(error => {
          this.$message.error('删除失败')
          console.log(error)
        })
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
      this.getUsers(currentPage, this.pageSize)
    },
    getUsers (currentPage, pageSize) {
      getUserList(currentPage, pageSize)
        .then(response => {
          const { list, total } = response.data
          this.userList = list
          this.total = total
        })
    }
  },
  mounted () {
    this.getUsers(this.currentPage, this.pageSize)
  }
}
</script>

<style scoped>
  .card {
    margin-left: 30px;
    margin-bottom: 30px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .delete-btn {
    float: right;
    padding: 3px 0;
    color: red;
  }
  .text-item {
    font-size: 12px;
    margin-bottom: 10px;
  }
  .pagination {
    float: right;
    margin-top: 10px;
  }
</style>
