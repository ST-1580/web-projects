<template>
    <div class="footer">
        Made by <a class="siteAuthor" href="https://github.com/ST-1580/">ST_1580</a>
    </div>
</template>

<script>
    export default {
        name: "Footer"
    }
</script>

<style scoped>
    .footer {
        position: absolute;
        bottom: 0.5rem;
        right: 0.7rem;
        color: #999999;
        font-size: 0.9rem;
    }

    .siteAuthor {
        color: #999999;
        text-decoration: none;
    }
</style>