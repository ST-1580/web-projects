<template>
    <div class="station"
         :id="station.uniqueName"
         v-on:mouseover="mouseOver()"
         v-on:click="mouseOver()">

    </div>
</template>

<script>
    export default {
        name: "Station",
        props: ["station", "len"],
        mounted() {
            const elem = document.getElementById(this.station.uniqueName);
            const deg = 90 + this.station.position * (360 / this.len);

            elem.style.setProperty('left', 'calc(50vw - var(--station-size) / 2 - '
                    + Math.cos(deg * Math.PI / 180).toString() + ' * var(--R-middle))');
            elem.style.setProperty('top', 'calc(50vh - var(--station-size) / 2 + '
                    + Math.sin(deg * Math.PI / 180).toString() + ' * var(--R-middle))');
            elem.style.setProperty('background-image', "url('img/" + this.station.id + ".png')");

        },
        methods: {
            mouseOver: function () {
                this.$root.$emit("changeStation", this.station);
            },
        }
    }
</script>

<style scoped>

</style>