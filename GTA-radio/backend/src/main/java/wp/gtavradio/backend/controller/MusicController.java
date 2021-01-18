package wp.gtavradio.backend.controller;

import org.springframework.web.bind.annotation.*;
import wp.gtavradio.backend.domain.Song;
import wp.gtavradio.backend.domain.SongTiming;
import wp.gtavradio.backend.domain.Station;
import wp.gtavradio.backend.service.StationService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MusicController {
    final long START_TIME = System.currentTimeMillis();
    private final StationService stationService;

    public MusicController(StationService stationService) {
        this.stationService = stationService;
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

    @GetMapping("/tracks")
    public List<SongTiming> getSongsList(@RequestParam int stationId) {
        List<SongTiming> timingList = new ArrayList<>();
        Station station = stationService.findById(stationId);
        long trackLen = station.getDuration();
        long shift = (System.currentTimeMillis() - START_TIME) % trackLen;
        for (Song song : station.getSongs()) {
            if (song.getStartTime() > shift) {
                SongTiming songTiming = getTiming(song, shift);
                songTiming.setStartTime(song.getStartTime() - shift);
                timingList.add(songTiming);
            } else if (song.getEndTime() > shift) {
                SongTiming songTiming = getTiming(song, shift);
                songTiming.setStartTime(0);
                timingList.add(songTiming);
            } else {
                SongTiming songTiming = getTiming(song, shift);
                songTiming.setStartTime(song.getStartTime() + station.getDuration() - shift);
                songTiming.setEndTime(song.getEndTime() + station.getDuration() - shift);
                timingList.add(songTiming);
            }
        }
        return timingList;
    }

    private SongTiming getTiming(Song song, long shift) {
        SongTiming songTiming = new SongTiming();
        songTiming.setAuthor(song.getAuthor().toUpperCase());
        songTiming.setName(song.getName());
        songTiming.setEndTime(song.getEndTime() - shift);
        return songTiming;
    }

}
