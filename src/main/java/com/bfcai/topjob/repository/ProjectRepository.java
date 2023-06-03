package com.bfcai.topjob.repository;

import com.bfcai.topjob.model.Course;
import com.bfcai.topjob.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query(value = "select distinct p from Project p where p.user.id=:userId")
    Set<Project> fetchAllProjectsForUser(@Param(value = "userId") Long userId);

    @Query(value = "delete from Project p where p.user.id=:userId")
    @Modifying
    @Transactional
    void deleteAllProjectsForUser(@Param(value = "userId") Long userId);

}
