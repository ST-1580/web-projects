<template>
    <div id="app">
        <Middle :stations="stations"
                :currentStation="currentStation"
                :currentSong="currentSong"/>
        <Footer/>
    </div>
</template>

<script>
    import Middle from "./components/Middle";
    import Footer from "./components/Footer";
    import axios from "axios"

    let timers = [];
    let maxWaitTime = -1;
    const audio = new Audio();

    function newSongName(here, res) {
        here.currentSong = {
            name: res.name,
            author: res.author
        }
    }

    function noSongName(here, time, id) {
        here.currentSong = null;
        if (maxWaitTime === time) {
            axios.get("/tracks", {params: {stationId: id}}).then(response => {
                const result = response.data;
                maxWaitTime = -1;
                for (let i = 0; i < result.length; i++) {
                    const res = result[i];
                    maxWaitTime = Math.max(maxWaitTime, res.endTime);
                    timers.push(setTimeout(noSongName, res.endTime, here, res.endTime, id));
                    timers.push(setTimeout(newSongName, res.startTime, here, res));
                }
            });
        }
    }

    export default {
        name: 'App',
        components: {
            Footer,
            Middle
        },
        data: function () {
            return {
                currentStation: {
                    name: "Radio Off",
                    id: 1,
                    uniqueName: "Off",
                    position: 0
                },
                stations: [],
                currentSong: null
            };
        },
        beforeCreate() {
            this.$root.$on("changeStation", (newStation) => {
                if (this.currentStation.uniqueName !== newStation.uniqueName) {
                    for (let i = 0; i < timers.length; i++) {
                        clearTimeout(timers[i]);
                    }
                    while (timers.length !== 0) {
                        timers.pop();
                    }
                    this.currentSong = null;
                    audio.pause();
                    audio.currentTime = 0;
                    maxWaitTime = -1;

                    document.getElementById(newStation.uniqueName).style.setProperty('opacity', 'var(--modified-opacity)');
                    document.getElementById(newStation.uniqueName).style.setProperty('border', 'var(--modified-border)');
                    document.getElementById(newStation.uniqueName).style.setProperty('z-index', 'var(--modified-z-index)');

                    document.getElementById(this.currentStation.uniqueName).style.setProperty('opacity', 'var(--classic-opacity)');
                    document.getElementById(this.currentStation.uniqueName).style.setProperty('border', 'var(--classic-border)');
                    document.getElementById(this.currentStation.uniqueName).style.setProperty('z-index', 'var(--classic-z-index)');

                    this.currentStation = newStation;

                    audio.src = 'music/ChangeStationSound.mp3';
                    audio.loop = false;
                    audio.play();

                    if (newStation.uniqueName !== 'Off') {
                        timers.push(setTimeout(function () {
                            audio.src = 'music/RadioStaticLoop.wav';
                            audio.loop = true;
                            audio.play();
                        }, 400));

                        axios.get("/musicSrc", {params: {stationId: newStation.id},}).then(response => {
                            if (response.data.toString() !== "No track") {
                                const src = response.data.toString();
                                const shift = Math.min(Math.random(), 0.6) * 1000 + 500;
                                const here = this;
                                axios.get("/tracks", {params: {stationId: newStation.id}}).then(response => {
                                    timers.push(setTimeout(function () {
                                        axios.get("/musicStartTime", {params: {stationId: newStation.id}}).then(response => {
                                            if (timers.length > 0) {
                                                clearTimeout(timers[0]);
                                            }
                                            audio.src = src;
                                            audio.currentTime = parseFloat(response.data.toString()) + shift / 1000;
                                            audio.loop = true;
                                            audio.play();
                                        });
                                        const result = response.data;
                                        for (let i = 0; i < result.length; i++) {
                                            const res = result[i];
                                            maxWaitTime = Math.max(maxWaitTime, res.endTime);
                                            timers.push(setTimeout(noSongName, res.endTime, here, res.endTime, newStation.id));
                                            timers.push(setTimeout(newSongName, res.startTime, here, res));
                                        }
                                    }, shift));
                                });
                            }
                        });
                    }
                }
            });

            this.$root.$on("allowAutoPlay", function () {
                this.canPlay = true;
            });

        },
        beforeMount() {
            axios.get("/radio").then(response => {
                this.stations = response.data;
            });
        },
    }
</script>
<style>

</style>
