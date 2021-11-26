package com.freestack.persistence.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer length;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Preview> previews;

    @ManyToMany(mappedBy = "moviesPlayedIn")
    private List<Actor> actorsPlayedIn;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public void addPreview(Preview preview){
        if(previews == null) {
            previews = new ArrayList<>();
        }
        previews.add(preview);
    }

    public List<Preview> getPreviews() {
        return previews;
    }

    public Movie setPreviews(List<Preview> previews) {
        this.previews = previews;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", length=").append(length);
        sb.append(", previews=").append(previews);
        sb.append(", actorsPlayedIn=").append(actorsPlayedIn);
        sb.append('}');
        return sb.toString();
    }
}
