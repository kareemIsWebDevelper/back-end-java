package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.CourseDTO;
import com.bfcai.topjob.model.Course;

import java.util.Set;

public interface CourseService {
    Set<Course> getAllCourses();

    Course getCourseById(Long courseId);

    Set<Course> getAllCoursesForUser(Long userId);

    Course saveOrUpdateCourse(Long courseId,CourseDTO courseDTO);

    Boolean deleteCourseById(Long courseId);

    void deleteAllCoursesForUser(Long userId);
}
