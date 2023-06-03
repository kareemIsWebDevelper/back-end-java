package com.bfcai.topjob.api;


import com.bfcai.topjob.dto.CourseDTO;
import com.bfcai.topjob.model.Course;
import com.bfcai.topjob.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseResource {

    private final CourseService courseService;

    @Autowired
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Set<Course>> getAllCourses() {
        return ResponseEntity.ok(this.courseService.getAllCourses());
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value = "courseId") Long courseId) {
        return ResponseEntity.ok(this.courseService.getCourseById(courseId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Set<Course>> getAllCoursesForUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.courseService.getAllCoursesForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDTO courseDTO) {
        Course savedCourse = this.courseService.saveOrUpdateCourse(null, courseDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(savedCourse.getCourseId()).toUri();
        return ResponseEntity.created(location).body(savedCourse);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Boolean> deleteCourseById(@PathVariable(value = "courseId") Long courseId) {
        return ResponseEntity.ok(this.courseService.deleteCourseById(courseId));
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<String> deleteAllCoursesForUser(@PathVariable(value = "userId") Long userId) {
        this.courseService.deleteAllCoursesForUser(userId);
        return ResponseEntity.ok("All course for user with id : " + userId + " deleted successfully");
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable(value = "courseId") Long courseId, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(this.courseService.saveOrUpdateCourse(courseId, courseDTO));
    }
}
