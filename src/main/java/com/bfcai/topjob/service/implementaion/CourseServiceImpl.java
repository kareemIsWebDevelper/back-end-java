package com.bfcai.topjob.service.implementaion;


import com.bfcai.topjob.dto.CourseDTO;
import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Course;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.CourseRepository;
import com.bfcai.topjob.service.CourseService;
import com.bfcai.topjob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserService userService, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Course> getAllCourses() {
        return new HashSet<>(this.courseRepository.findAll());
    }

    @Override
    public Course getCourseById(Long courseId) {
        return this.courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("No course found with id : " + courseId));
    }

    @Override
    public Set<Course> getAllCoursesForUser(Long userId) {
        return this.courseRepository.fetchAllCoursesForUser(userId);
    }

    @Override
    public Course saveOrUpdateCourse(Long courseId, CourseDTO courseDTO) {
        UserResponseDTO userResponseDTO = this.userService.getUserById(courseDTO.getUserId());
        User user = new User();
        user.setId(userResponseDTO.getId());
        Course course = this.modelMapper.map(courseDTO, Course.class);
        if (courseId != null) {
            this.getCourseById(courseId);
            course.setCourseId(courseId);
        }
        course.setUser(user);
        return this.courseRepository.save(course);
    }

    @Override
    public Boolean deleteCourseById(Long courseId) {
        Course course=this.courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return false;
        }
        this.courseRepository.deleteById(courseId);
        return true;
    }

    @Override
    public void deleteAllCoursesForUser(Long userId) {
        this.userService.getUserById(userId);
        this.courseRepository.deleteAllCoursesForUser(userId);
    }
}
