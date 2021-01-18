package wp.gtavradio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import wp.gtavradio.backend.domain.Station;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM station WHERE hidden=false", nativeQuery = true)
    List<Station> findAllNotHidden();

    Station findById(int id);
}
