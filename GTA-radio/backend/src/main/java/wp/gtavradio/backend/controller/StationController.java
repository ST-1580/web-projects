package wp.gtavradio.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wp.gtavradio.backend.domain.Station;
import wp.gtavradio.backend.service.StationService;

import java.util.List;

@RestController
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/radio")
    public List<Station> findStations() {
        System.out.println(1);
        return stationService.findAllNotHidden();
    }
}
