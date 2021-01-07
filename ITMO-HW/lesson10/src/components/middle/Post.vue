<template>
    <div class="fullPost">
        <MiddlePost :post="post" :userName="userName" :commCount="comments.length"/>
        <div class="comment-list">Comments:</div>
        <Comment v-for="comment in comments" :author="getAuthor(comment)" :comment="comment" :key="comment.id" />
    </div>
</template>

<script>
    import Comment from "./Comment";
    import MiddlePost from "./MiddlePost";
    export default {
        name: "Post",
        components: {
            MiddlePost,
            Comment
        },
        props: ["post", "userName", "comments", "users"],
        methods: {
            getAuthor: function (comment) {
                return this.users[comment.userId].name
            }
        }
    }
</script>

<style scoped>
</style>