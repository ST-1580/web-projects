<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="reversePosts" :users="users" :comments="comments"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <Post v-if="page === 'Post'" :post="post"
                                         :userName="users[post.userId].name"
                                         :comments="filterComments(post.id)"
                                         :users="filterUsers(filterComments(post.id))"/>
        </main>
    </div>
</template>

<script>
    import Sidebar from "@/components/sidebar/Sidebar";
    import Index from "@/components/middle/Index";
    import Enter from "@/components/middle/Enter";
    import WritePost from "@/components/middle/WritePost";
    import EditPost from "@/components/middle/EditPost";
    import Register from "@/components/middle/Register";
    import Post from "@/components/middle/Post";
    import Users from "@/components/usersTable/Users";

    export default {
        name: "Middle",
        data: function () {
            return {
                page: "Index",
                post: null
            }
        },
        components: {
            Post,
            Users,
            Register,
            WritePost,
            Enter,
            Index,
            Sidebar,
            EditPost
        },
        props: ["posts", "users", "comments"],
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            },
            reversePosts: function () {
                return Object.values(this.posts).reverse();
            }
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page) => this.page = page);
            this.$root.$on("onChangePageToPost", (page, post) => {
                this.page = page;
                this.post = post
            })
        },
        methods: {
            filterComments: function (id) {
                return Object.values(this.comments).filter(a => a.postId === id)
            },
            filterUsers : function (neededComments) {
                const usr = this.users;
                let neededUsers = {};
                neededComments.forEach(function (comm) {
                    neededUsers[comm.userId] = usr[comm.userId];
                });
                return neededUsers;
            }
        }
    }
</script>

<style scoped>

</style>