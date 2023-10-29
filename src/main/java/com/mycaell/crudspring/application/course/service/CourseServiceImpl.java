package com.mycaell.crudspring.application.course.service;

import com.mycaell.crudspring.application.shared.exception.ResourceNotFoundException;
import com.mycaell.crudspring.domain.course.enums.Category;
import com.mycaell.crudspring.domain.course.model.Course;
import com.mycaell.crudspring.domain.course.service.ICourseService;
import com.mycaell.crudspring.infrastructure.persistence.repository.CourseRepository;

import com.mycaell.crudspring.infrastructure.util.BeanUtils;
import com.mycaell.crudspring.presentation.enums.dto.SelectOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Course savedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course"));

        BeanUtils.copyNonNullProperties(course, savedCourse);

        return courseRepository.save(savedCourse);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course"));
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course")));
    }

    @Override
    public List<SelectOption<String>> getCategories() {
        var enumValues = Category.values();

        List<SelectOption<String>> categories = new ArrayList<>();

        for (var c : enumValues) {
            categories.add(new SelectOption<>(c.getValue(), c.name()));
        }

        return categories;
    }
}
