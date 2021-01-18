package wp.gtavradio.backend.controller;

import org.springframework.web.bind.annotation.*;
import wp.gtavradio.backend.domain.JustSong;
import wp.gtavradio.backend.domain.Song;
import wp.gtavradio.backend.domain.Station;
import wp.gtavradio.backend.service.SongService;
import wp.gtavradio.backend.service.StationService;

@RestController
public class MusicController {
    final long START_TIME = System.currentTimeMillis();
    private final StationService stationService;
    private final SongService songService;

    public MusicController(StationService stationService, SongService songService) {
        this.stationService = stationService;
        this.songService = songService;
    }

    @GetMapping("/musicSrc")
    public String getMusicSrc(@RequestParam int stationId) {
        long startTime = System.currentTimeMillis();
        Station station = stationService.findById(stationId);
        if (station == null) {
            return "No track";
        }
        if (System.currentTimeMillis() - startTime >= 2000 || station.getFile() == null) {
            return "No track";
        }
        return station.getFile();
    }

    @GetMapping("/musicStartTime")
    public double getMusicStartTime(@RequestParam int stationId) {
        Station station = stationService.findById(stationId);
        long trackLen = station.getDuration();
        long shift = (System.currentTimeMillis() - START_TIME) % trackLen;
        return (double) shift / 1000;
    }

    @GetMapping("/track")
    public JustSong getCurrentSong(@RequestParam int stationId, @RequestParam String currentTime) {
        Station station = stationService.findById(stationId);
        long shift = (System.currentTimeMillis() - START_TIME) % station.getDuration();
        Song song = songService.getCurrentSong(stationId, currentTime.equals("-1") ? shift : (long) Double.parseDouble(currentTime));
        JustSong justSong = new JustSong();
        if (song == null) {
            justSong.setAuthor("");
            justSong.setName("");
        } else {
            justSong.setAuthor(song.getAuthor());
            justSong.setName(song.getName());
        }
        return justSong;
    }
}
