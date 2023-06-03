package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query(value = "select distinct e from Education e where e.user.id=:userId")
    Set<Education> fetchAllEducationForUser(@Param(value = "userId") Long userId);

    @Query(value = "delete from Education e where e.user.id=:userId")
    @Modifying
    @Transactional
    void deleteAllEducationForUser(@Param(value = "userId") Long userId);
}
