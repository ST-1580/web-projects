<template>
  <div>
    <MiddlePost v-for="post in posts" :post="post"
                                      :key="post.id"
                                      :userName="users[post.userId].name"
                                      :commCount="commentsCount(post.id)"/>
  </div>
</template>

<script>
  import MiddlePost from "./MiddlePost";

  export default {
    name: "Index",
    components: {
        MiddlePost
    },
    props: ["posts", "users", "comments"],
    methods: {
      commentsCount: function (id) {
        return Object.values(this.comments).filter(a => a.postId === id).length;
      }
    }
}
</script>

<style scoped>

</style>