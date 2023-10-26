package com.mycaell.crudspring.application.course.controller;

import com.mycaell.crudspring.domain.course.enums.Category;
import com.mycaell.crudspring.domain.course.model.Course;
import com.mycaell.crudspring.domain.course.service.ICourseService;
import com.mycaell.crudspring.infrastructure.converter.service.IConverterService;
import com.mycaell.crudspring.presentation.course.dto.CourseRequest;
import com.mycaell.crudspring.presentation.course.dto.CourseResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final ICourseService courseService;
    private final IConverterService converterService;

    public CourseController(ICourseService courseService, IConverterService converterService) {
        this.courseService = courseService;
        this.converterService = converterService;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody @Valid @NotNull CourseRequest courseRequest) {
        Course course = converterService.convert(courseRequest, Course.class);
        Course savedCourse = courseService.create(course);
        CourseResponse courseResponse = converterService.convert(savedCourse, CourseResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable @NotNull @Positive Long id,
                                                 @RequestBody @Valid @NotNull CourseRequest courseRequest) {
        Course course = converterService.convert(courseRequest, Course.class);
        Course savedCourse = courseService.update(id, course);
        CourseResponse courseResponse = converterService.convert(savedCourse, CourseResponse.class);

        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll() {
        List<Course> courses = courseService.findAll();
        List<CourseResponse> coursesResponse = converterService.convert(courses, CourseResponse.class);

        return ResponseEntity.ok(coursesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable @NotNull @Positive Long id) {
        Course course = courseService.findById(id);
        CourseResponse courseResponse = converterService.convert(course, CourseResponse.class);

        return ResponseEntity.ok(courseResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = courseService.getCategories();

        return ResponseEntity.ok(categories);
    }

}
