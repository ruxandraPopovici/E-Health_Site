package com.example.demo.medic;

import javax.persistence.*;

@Entity
@Table
public class Medic {

    @Id
    @SequenceGenerator(
            name = "medic_sequence",
            sequenceName = "medic_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medic_sequence"
    )

    private Long medicID;
    private String name;
    private String specialization;
    private String CV;
    private float rating;
    private String review;
    private Long userID;

    public Medic(Long medicID, String name, String specialization, String CV, float rating, String review, Long userID) {
        this.medicID = medicID;
        this.name = name;
        this.specialization = specialization;
        this.CV = CV;
        this.rating = rating;
        this.review = review;
        this.userID = userID;
    }

    public Medic(String name, String specialization, String CV, float rating, String review, Long userID) {
        this.name = name;
        this.specialization = specialization;
        this.CV = CV;
        this.rating = rating;
        this.review = review;
        this.userID = userID;
    }

    public Medic(String name){
        this.name = name;
    }

    public Medic(){

    }

    public Long getMedicID() {
        return medicID;
    }
    public void setMedicID(Long medicID) {
        this.medicID = medicID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCV() {
        return CV;
    }
    public void setCV(String CV) {
        this.CV = CV;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
