package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.Course;
import com.bfcai.topjob.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    @Query(value = "select distinct s from Skill s where s.user.id=:userId")
    Set<Skill> fetchAllSkillsForUser(@Param(value = "userId") Long userId);

    @Query(value = "delete from Skill s where s.user.id=:userId")
    @Modifying
    @Transactional
    void deleteAllSkillsForUser(@Param(value = "userId") Long userId);

}
