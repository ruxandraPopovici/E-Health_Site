package com.example.demo.medic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
    @Query("SELECT m FROM Medic m WHERE m.name = ?1")
    Optional<Medic> findMedicByName(String name);

    @Query("SELECT p FROM Medic p where p.userID = ?1")
    Optional<Medic> findMedicByUserID(Long userID);

    @Query("SELECT p FROM Medic p where p.medicID <> ?1")
    Optional<Medic> findDistinctByMedicID(Long medicID);
}
