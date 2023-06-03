package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {

    @Query(value = "select distinct e from Experience e where e.user.id=:userId")
    Set<Experience> fetchAllExperienceForUser(Long userId);

    @Query(value = "delete from Experience e where e.user.id=:userId")
    @Modifying
    @Transactional
    void deleteAllExperienceForUser(@Param(value = "userId") Long userId);
}
