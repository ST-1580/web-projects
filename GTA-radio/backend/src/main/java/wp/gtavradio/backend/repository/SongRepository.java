package wp.gtavradio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import wp.gtavradio.backend.domain.Song;
import wp.gtavradio.backend.domain.Station;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Transactional
    @Query(value = "SELECT * FROM song WHERE station_id=?1 and start_time<=?2 and end_time>=?2", nativeQuery = true)
    Song findCurrentSong(int stationId, long currentTime);
}
