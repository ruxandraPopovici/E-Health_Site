package com.example.demo.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //responsible for data access
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.CNP = ?1")
    Optional<Patient> findPatientByCNP(String CNP);

    @Query("SELECT p FROM Patient p where p.patientID = ?1")
    Optional<Patient> findPatientByPatientID(Long ID);

    @Query("SELECT p FROM Patient p where p.telNo = ?1")
    Optional<Patient> findPatientByTelNo(String telNo);

    @Query("SELECT p FROM Patient p where p.patientID <> ?1")
    Optional<Patient> findDistinctByPatientID(Long patientID);

    @Query("SELECT p FROM Patient p where p.userID = ?1")
    Optional<Patient> findPatientByUserID(Long userID);
}
