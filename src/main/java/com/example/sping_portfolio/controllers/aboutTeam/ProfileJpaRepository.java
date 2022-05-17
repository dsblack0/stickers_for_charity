package com.example.sping_portfolio.controllers.aboutTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileJpaRepository extends JpaRepository<Profile, Long>{
    List<Profile> findAllByOrderByNameAsc();

    List<Profile> findByNameContainingIgnoreCase(String name);

    @Query(
            value = "SELECT * FROM Profile p WHERE p.name LIKE ?1", nativeQuery = true
    )
    List<Profile> findByLikeTermNative(String term);
}
