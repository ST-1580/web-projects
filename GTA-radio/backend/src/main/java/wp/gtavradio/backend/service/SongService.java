package wp.gtavradio.backend.service;

import org.springframework.stereotype.Service;
import wp.gtavradio.backend.domain.Song;
import wp.gtavradio.backend.repository.SongRepository;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song getCurrentSong (int stationId, long currentTime) {
        return songRepository.findCurrentSong(stationId, currentTime);
    }
}
