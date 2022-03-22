package com.example.demo.patient;

import javax.persistence.*;

@Entity
@Table
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patientID;
    private String firstName;
    private String lastName;
    private String CNP;
    private String address;
    private String telNo;
    private Long userID;

    public Patient(Long patientID, String firstName, String lastName, String CNP, String address, String telNo, Long userID) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.address = address;
        this.telNo = telNo;
        this.userID = userID;
    }

    public Patient(String firstName, String lastName, String CNP, String address, String telNo, Long userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.address = address;
        this.telNo = telNo;
        this.userID = userID;
    }

    public Patient(String firstName, String lastName, String CNP, String address, String telNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.address = address;
        this.telNo = telNo;
    }

    public Patient(String firstName, String secondName){
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public Patient() {

    }

    public Long getPatientID() {
        return patientID;
    }
    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }
    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
