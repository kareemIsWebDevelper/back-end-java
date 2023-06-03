package com.bfcai.topjob.repository;


import com.bfcai.topjob.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select distinct c from Course c where c.user.id=:userId")
    Set<Course> fetchAllCoursesForUser(@Param(value = "userId") Long userId);

    @Query(value = "delete from Course c where c.user.id=:userId")
    @Modifying
    @Transactional
    void deleteAllCoursesForUser(@Param(value = "userId") Long userId);
}
