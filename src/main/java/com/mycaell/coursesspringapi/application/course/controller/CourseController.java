package com.mycaell.coursesspringapi.application.course.controller;

import com.mycaell.coursesspringapi.domain.course.model.Course;
import com.mycaell.coursesspringapi.domain.course.service.ICourseService;
import com.mycaell.coursesspringapi.infrastructure.converter.service.IConverterService;
import com.mycaell.coursesspringapi.presentation.course.dto.CourseRequest;
import com.mycaell.coursesspringapi.presentation.course.dto.CourseResponse;

import com.mycaell.coursesspringapi.presentation.enums.dto.SelectOption;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<SelectOption<String>>> getCategories() {
        List<SelectOption<String>> categories = courseService.getCategories();

        return ResponseEntity.ok(categories);
    }

}
