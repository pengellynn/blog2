<template>
  <div>
    <el-table
      :data="tableData"
      :default-sort = "{prop: 'date', order: 'descending'}"
      @selection-change="handleSelectionChange"
      style="width: 100%"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        prop="id"
        label="ID"
        width="60"
      />
      <el-table-column
        prop="title"
        label="标题"
        width="120"
        sortable
      />
      <el-table-column
        prop="author"
        label="作者"
        width="100"
        sortable
        show-overflow-tooltip
      />
      <el-table-column
        prop="subCategoryName"
        label="类别"
        width="100"
        sortable
        show-overflow-tooltip
      />
      <el-table-column
        prop="date"
        label="日期"
        sortable>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="handleEdit(scope.row)"
          >编辑</el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="delete-all-btn">
      <el-button
        type="danger"
        size="small"
        @click="deleteAllSelect"
        v-show="isShow"
      >批量删除</el-button>
    </div>
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
import { getArticleList, deleteArticle, batchDeleteArticle } from '@/utils/api'
export default {
  name: 'ArticleList',
  data () {
    return {
      loading: true,
      total: 50,
      pageSize: 5,
      currentPage: 1,
      tableData: [],
      multipleSelection: []
    }
  },
  computed: {
    isShow () {
      return this.multipleSelection.length > 0
    }
  },
  methods: {
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    handleEdit (row) {
      this.$router.push({ path: `/article/edit/${row.id}` })
    },
    handleDelete (row) {
      deleteArticle(row.id)
        .then(response => {
          if (response.data.status === 'success') {
            this.tableData = this.tableData.filter(item => item.id !== row.id)
            // this.tableData.forEach((item, index) => {
            //   if (item.id === row.id) {
            //     this.tableData.splice(index, 1)
            //   }
            // })
          }
        })
        .catch(error => {
          this.$message.error('删除失败')
          console.log(error)
        })
    },
    deleteAllSelect () {
      const ids = []
      this.multipleSelection.forEach(item => {
        ids.push(item.id)
      })
      batchDeleteArticle(ids)
        .then(response => {
          if (response.data.status === 'success') {
            this.tableData = this.tableData.filter(item => !this.multipleSelection.includes(item))
          }
        })
        .catch(error => {
          this.$message.error('批量删除失败')
          console.log(error)
        })
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
      this.getArticles(currentPage, this.pageSize)
    },
    getArticles (currentPage, pageSize) {
      getArticleList(currentPage, pageSize)
        .then(response => {
          const { list, total } = response.data
          this.tableData = list
          this.total = total
        })
    }
  },
  mounted () {
    this.getArticles(this.currentPage, this.pageSize)
  }
}
</script>

<style scoped>
  .delete-all-btn {
    float: left;
    margin-top: 20px;
  }
  .pagination {
    float: right;
    margin-top: 20px;
  }
</style>
