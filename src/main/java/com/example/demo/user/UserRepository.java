package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {

    @Query("SELECT u from UserTable u WHERE u.userName = ?1")
    Optional<UserTable> findUserByUserName(String userName);

    @Modifying
    @Query("UPDATE UserTable u SET u.isActive = false")
    void setAllInactive();

    @Modifying
    @Query("UPDATE UserTable u SET u.isActive = true where u.userID = ?1")
    void setActive(Long id);

    @Query("SELECT u from UserTable u WHERE u.isActive = true")
    Optional<UserTable> findActiveUser();
}
