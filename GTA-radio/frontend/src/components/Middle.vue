<template>
    <div class="middle">
        <div v-if="!canPlay" class="allowAutoPlay" v-on:click="allowAutoPlay()">
            Click somewhere to allow auto play
        </div>
        <div class="radio" v-if="canPlay">
            <Station v-for="station in stations"
                     :station="station"
                     :len="Object.values(stations).length"
                     :key="station.id" />
            <InfoPanel v-if="hasStations()" class="downInfo" :station="currentStation" :song="currentSong"/>
            <InfoPanel v-if="hasStations()" class="upInfo" :station="currentStation" :song="currentSong"/>
        </div>
    </div>
</template>

<script>
    import Station from "./Station";
    import InfoPanel from "./InfoPanel";
    export default {
        name: "Middle",
        components: {InfoPanel, Station},
        props: ["stations", "currentStation", "currentSong"],
        data: function() {
            return {
                canPlay: false
            }
        },
        methods: {
            hasStations: function() {
                return Object.values(this.stations).length !== 0;
            },
            allowAutoPlay: function () {
                this.canPlay = true;
            }
        },
        beforeUpdate() {
            const numOfStations = Object.values(this.stations).length;
            const stylesheet = document.styleSheets[1];
            let cssRoot;
            for (let i = 0; i < stylesheet.cssRules.length; i++) {
                if (stylesheet.cssRules[i].selectorText === ':root') {
                    cssRoot = stylesheet.cssRules[i];
                }
            }

            cssRoot.style.setProperty('--sinus-half', Math.sin(Math.PI / numOfStations).toString());
            cssRoot.style.setProperty('--station-block-size',
                'min(17vmin, calc((8 * var(--sinus-half) * 100vmin) / (9 * var(--sinus-half) + 8)))');
            cssRoot.style.setProperty('--station-border-size', 'calc(var(--station-block-size) / 16)');
            cssRoot.style.setProperty('--station-size',
                'calc(var(--station-block-size) + 2 * var(--station-border-size))');
            cssRoot.style.setProperty('--R-middle',
                'calc((100vmin - var(--station-block-size) - 2 * var(--station-border-size)) / 2)');
            cssRoot.style.setProperty('--left-const', 'calc(50vw - var(--station-size) / 2');
            cssRoot.style.setProperty('--left-const', 'calc(50vh - var(--station-size) / 2');
        },
        updated() {
            const id = this.currentStation.uniqueName;
            const elem = document.getElementById(id);

            elem.style.setProperty('opacity', 'var(--modified-opacity)');
            elem.style.setProperty('border', 'var(--modified-border)');
            elem.style.setProperty('z-index', 'var(--modified-z-index)');
        }
    }
</script>

<style scoped>

</style>