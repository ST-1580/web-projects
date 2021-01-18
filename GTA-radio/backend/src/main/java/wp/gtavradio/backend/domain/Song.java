package wp.gtavradio.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Song {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

    @NotEmpty
    private String name;

    @NotEmpty
    private String author;

    private long startTime;

    private long endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
