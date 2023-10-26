package com.mycaell.crudspring.application.course.service;

import com.mycaell.crudspring.application.course.service.exception.ResourceNotFoundException;
import com.mycaell.crudspring.domain.course.enums.Category;
import com.mycaell.crudspring.domain.course.model.Course;
import com.mycaell.crudspring.domain.course.service.ICourseService;
import com.mycaell.crudspring.infrastructure.persistence.repository.CourseRepository;

import com.mycaell.crudspring.infrastructure.util.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Course savedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course"));

        BeanUtils.copyNonNullProperties(course, savedCourse);

        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        Optional<Course> entityOpt = courseRepository.findById(id);
        return entityOpt.orElseThrow(() -> new ResourceNotFoundException("Course"));
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = Stream.of(Category.values())
                .map(e -> e.getValue())
                .collect(Collectors.toList());

        return categories;
    }
}
