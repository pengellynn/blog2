<template>
  <article-detail :isEdit="false"/>
</template>

<script>
import ArticleDetail from './ArticleDetail'
export default {
  name: 'CreateArticle',
  components: { ArticleDetail }
}
</script>
