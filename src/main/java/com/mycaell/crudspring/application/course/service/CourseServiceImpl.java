package com.mycaell.crudspring.application.course.service;

import com.mycaell.crudspring.application.course.service.exception.ResourceNotFoundException;
import com.mycaell.crudspring.domain.course.model.Course;
import com.mycaell.crudspring.domain.course.service.ICourseService;
import com.mycaell.crudspring.infrastructure.persistence.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        Optional<Course> entityOpt = courseRepository.findById(id);
        return entityOpt.orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
