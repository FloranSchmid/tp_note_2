package fr.univtours.polytech.tp_note_2.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class MovieBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String title;
    private Integer note;

    @JsonIgnore
    @Transient
    private String increaseURL;

    @JsonIgnore
    @Transient
    private String decreaseURL;

    @Transient
    private String actors;

    @Transient
    private Integer year;

    @Transient
    private String imgPoster;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getIncreaseURL() {
        return increaseURL;
    }

    public void setIncreaseURL(String increaseURL) {
        this.increaseURL = increaseURL;
    }

    public String getDecreaseURL() {
        return decreaseURL;
    }

    public void setDecreaseURL(String decreaseURL) {
        this.decreaseURL = decreaseURL;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(String imgPoster) {
        this.imgPoster = imgPoster;
    }

}
