<template>
  <div>
    <el-form v-model="article">
      <el-row class="head">
        <el-col :span="2">
          <el-button type="primary" style="float: right;" @click="handleSave">保存</el-button>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-select
              v-model="article.subCategoryId"
              placeholder="类别"
            >
              <el-option
                v-for="item in subCategories"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-select
              v-model="article.categoryId"
              placeholder="栏目"
            >
              <el-option
                v-for="item in categories"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="标题" label-width="60px">
            <el-input
              class="title-input"
              placeholder="输入标题"
              v-model="article.title"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="摘要" label-width="60px">
            <el-input
              class="summary-input"
              type="textarea"
              :autosize="{ minRows: 1, maxRows: 3}"
              v-model="article.summary"
              placeholder="输入摘要"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6" style="margin-right: 50px">
          <el-form-item label="作者" label-width="60px">
            <el-input
              placeholder="输入作者"
              v-model="article.author"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="标签" label-width="60px">
            <el-select
              style="width: 300px;"
              v-model="article.tagNames"
              multiple
              filterable
              allow-create
              placeholder="添加文章标签"
            >
              <el-option
                v-for="item in tags"
                :key="item.id"
                :label="item.name"
                :value="item.name"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item style="padding-left: 20px">
            <mavon-editor
              class="mavon-editor"
              ref=md
              @imgAdd="imgAdd"
              @imgDel="imgDel"
              v-model="article.content"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8080/imgUpload"
        name="image"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
        :on-remove="handleRemove"
      >
        <img v-if="article.icon" :src="article.icon" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form>
  </div>
</template>

<script>
import request from '@/utils/request'
import { getArticle, updateArticle, addArticle, getAllCategory, getAllSubCategory, getAllTag } from '@/utils/api'
export default {
  name: 'ArticleDetail',
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      categories: [],
      subCategories: [],
      tags: [],
      article: {
        id: '',
        title: '',
        author: '',
        date: '',
        view: 0,
        love: 0,
        categoryId: '',
        subCategoryId: '',
        summary: '',
        content: '',
        tagNames: [],
        icon: ''
      }
    }
  },
  methods: {
    imgAdd (pos, $file) {
      // 第一步.将图片上传到服务器.
      let formData = new FormData()
      formData.append('image', $file)
      request({
        url: '/imgUpload',
        method: 'post',
        data: formData,
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then((url) => {
        this.$img2Url(pos, url)
      })
    },
    imgDel (pos) {
      delete this.img_file[pos]
    },
    getCategories () {
      getAllCategory().then(response => {
        this.categories = response.data
      })
    },
    getSubCategories () {
      getAllSubCategory().then(response => {
        this.subCategories = response.data
      })
    },
    getTags () {
      getAllTag().then(response => {
        this.tags = response.data
      })
    },
    getArticle (id) {
      getArticle(id).then(response => {
        this.article = response.data
      })
    },
    handleSave () {
      if (this.isEdit) {
        //console.log(this.$refs.md.d_render)
        updateArticle(this.article.id, this.article)
          .then(response => {
            if (response.data.status === 'success') {
              this.$message.success('修改成功')
              this.$router.push('/article/list')
            }
          })
          .catch(error => {
            this.$message.error('保存失败')
            console.log(error.toString())
          })
      } else {
        addArticle(this.article)
          .then(response => {
            if (response.data.status === 'success') {
              this.$message.success('添加成功')
              this.$router.push('/article/list')
            }
          })
          .catch(error => {
            this.$message.error('添加失败')
            console.log(error.toString())
          })
      }
    },
    handleRemove (file, fileList) {
      this.$message.success('移除成功')
    },
    handleAvatarSuccess (res, file) {
      this.$message.success('上传成功')
      this.article.icon = res.data
      console.log(this.article.icon)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isGIF = file.type === 'image/gif'
      const isPNG = file.type === 'image/png'
      const isBMP = file.type === 'image/bmp'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG && !isGIF && !isPNG && !isBMP) {
        this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!')
      }
      return (isJPG || isBMP || isGIF || isPNG) && isLt2M
    }
  },
  created () {
    if (this.isEdit) {
      const id = this.$router.currentRoute.params.id
      this.getArticle(id)
    }
    this.getCategories()
    this.getSubCategories()
    this.getTags()
  }
}
</script>

<style scoped>
  .head {
    display: flex;
    flex-direction: row-reverse;
    margin-bottom: 10px;
  }
  .el-form {
    font-weight: bold;
  }
  .el-select /deep/ input {
    font-size: 15px;
  }
  .title-input /deep/ input {
    border: none;
    border-bottom: 1px solid;
    border-radius: 0;
  }
  .summary-input /deep/ textarea {
    border: none;
    border-bottom: 1px solid;
    border-radius: 0;
    font-family: 微软雅黑, sans-serif;
  }
  .mavon-editor {
    height: 400px;
    width: 100%;
  }
  .avatar-uploader /deep/ .el-upload {
    margin-left: 20px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader /deep/ .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
