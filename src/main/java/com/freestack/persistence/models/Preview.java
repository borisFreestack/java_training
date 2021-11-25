package com.freestack.persistence.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Preview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String city;
    private Integer numberOfSeats;
    @ManyToOne
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Preview setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Preview{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", city='").append(city).append('\'');
        sb.append(", numberOfSeats=").append(numberOfSeats);
        sb.append('}');
        return sb.toString();
    }
}
