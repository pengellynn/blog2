<template>
  <div>
    <div>
      <el-form v-model="category">
        <el-row>
          <el-col :span="4" >
            <el-form-item>
              <el-input v-model="category.name" placeholder="输入name"/>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-input v-model="category.url" placeholder="输入URL"/>
            </el-form-item>
          </el-col>
          <el-button type="primary" @click="addCategory">添加</el-button>
        </el-row>
      </el-form>
      <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort = "{prop: 'id', order: 'descending'}"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
        />
        <el-table-column
          prop="id"
          label="ID"
          width="80"
        />
        <el-table-column
          prop="name"
          label="Name"
          width="120"
          sortable
        >
          <template slot-scope="scope">
            <el-input
              v-if="showEdit[scope.$index]"
              v-model="scope.row.name"
              @keyup.enter.native="handleSave(scope.row, scope.$index)"
            >
            </el-input>
            <span v-else>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="url"
          label="URL"
          sortable>
          <template slot-scope="scope">
            <el-input
              v-if="showEdit[scope.$index]"
              v-model="scope.row.url"
              @keyup.enter.native="handleSave(scope.row, scope.$index)"
            >
            </el-input>
            <span v-else>{{scope.row.url}}</span>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="200">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="primary"
              @click="handleEdit(scope.$index)"
            >编辑</el-button>
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="delete-all-btn">
      <el-button type="danger" size="small" @click="deleteAllSelect" v-show="isShow">批量删除</el-button>
    </div>
  </div>
</template>

<script>
import { getAllCategory, addCategory, updateCategory, deleteCategory, batchDeleteCategory } from '@/utils/api'
export default {
  name: 'Column',
  data () {
    return {
      category: {
        name: '',
        url: ''
      },
      multipleSelection: [],
      showEdit: [],
      tableData: []
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
    handleEdit (index) {
      this.$set(this.showEdit, index, true)
    },
    handleSave (row, index) {
      updateCategory(row.id, row)
        .then(response => {
          if (response.data.status === 'success') {
            this.tableData = this.tableData.map(item => {
              if (item.id === row.id) {
                item = row
                this.$set(this.showEdit, index, false)
              }
              return item
            })
          }
        })
        .catch(error => {
          this.$message.error('更新失败')
          console.log(error)
        })
    },
    handleDelete (row) {
      deleteCategory(row.id)
        .then(response => {
          if (response.data.status === 'success') {
            this.tableData.forEach((item, index) => {
              if (item.id === row.id) {
                this.tableData.splice(index, 1)
              }
            })
            this.$message.success('删除成功')
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
      batchDeleteCategory(ids)
        .then(response => {
          if (response.data.status === 'success') {
            this.tableData = this.tableData.filter(item => !this.multipleSelection.includes(item))
            this.$message.success('批量删除成功')
          }
        })
        .catch(error => {
          this.$message.error('批量删除失败')
          console.log(error)
        })
    },
    addCategory () {
      addCategory(this.category)
        .then(response => {
          if (response.data.status === 'success') {
            getAllCategory().then(response => {
              this.tableData = response.data
            })
            this.$message.success('添加成功')
          }
        })
        .catch(error => {
          this.$message.error('添加失败')
          console.log(error)
        })
    }
  },
  created () {
    getAllCategory().then(response => {
      this.tableData = response.data
    })
  }
}
</script>

<style scoped>
.el-form-item {
  margin-right: 20px;
}
.delete-all-btn {
  margin-top: 20px;
}
</style>
