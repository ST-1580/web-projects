package wp.gtavradio.backend.service;

import org.springframework.stereotype.Service;
import wp.gtavradio.backend.domain.Station;
import wp.gtavradio.backend.repository.StationRepository;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> findAllNotHidden() {
        return stationRepository.findAllNotHidden();
    }

    public Station findById(int stationId) {
        return stationRepository.findById(stationId);
    }
}
