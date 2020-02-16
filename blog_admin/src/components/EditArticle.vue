<template>
  <article-detail :isEdit="true"/>
</template>

<script>
import ArticleDetail from './ArticleDetail'
export default {
  name: 'EditArticle',
  components: { ArticleDetail }
}
</script>
