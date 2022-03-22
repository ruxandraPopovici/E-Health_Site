package com.example.demo.user;

import javax.persistence.*;

@Entity
@Table
public class UserTable {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Long userID;
    private String userName;
    private String password;
    private boolean isPatient;
    private boolean isActive;

    public UserTable(Long userID, String userName, String password, boolean isPatient, boolean isActive) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.isPatient = isPatient;
        this.isActive = isActive;
    }
    public UserTable(String userName, String password, boolean isPatient, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.isPatient = isPatient;
        this.isActive = isActive;
    }
    public UserTable(){}

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPatient() {
        return isPatient;
    }
    public void setPatient(boolean patient) {
        isPatient = patient;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
