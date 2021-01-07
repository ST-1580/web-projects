<template>
    <div class="comment">
        <div class="info">By {{ author }}</div>
        <div class="text">{{ comment.text }}</div>
    </div>
</template>

<script>
    export default {
        name: "Comment",
        props: ["author", "comment"]
    }
</script>

<style scoped>
    .comment {
        margin: 1em 0 0 0.5em;
        border-left: 3px solid var(--border-color);
        padding-left: 0.25em;
    }

    .comment .info {
        font-size: 0.85rem;
        color: #888;
        margin-bottom: 0.5em;
    }

    .comment-form .error {
        color: var(--error-color);
        font-size: 0.75rem;
    }
</style>