<template>
    <div class="infoPanel">
        <div class="info" id="infoStationName">
            {{ station.name }}
        </div>
        <div class="info" id="infoSongAuthor">
            {{ song === null ? "" : song.author }}
        </div>
        <div class="info" id="infoSongName">
            {{ song === null ? "" : song.name }}
        </div>
    </div>
</template>

<script>
    export default {
        name: "InfoPanel",
        props: ["station", "song"]
    }
</script>

<style scoped>

</style>