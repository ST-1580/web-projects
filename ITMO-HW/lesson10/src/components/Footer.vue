<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2020 by Mike Mirzayanov
        <div>Total users: {{ usersSize }}. Total posts: {{ postsSize }}</div>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersSize", "postsSize"]
}
</script>

<style scoped>
    div {
        margin-top: 0.75em
    }
</style>