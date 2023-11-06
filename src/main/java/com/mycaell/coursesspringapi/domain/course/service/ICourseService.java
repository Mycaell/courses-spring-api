package com.mycaell.coursesspringapi.domain.course.service;

import com.mycaell.coursesspringapi.domain.course.model.Course;
import com.mycaell.coursesspringapi.domain.shared.service.CrudService;
import com.mycaell.coursesspringapi.presentation.enums.dto.SelectOption;

import java.util.List;

public interface ICourseService extends CrudService<Course, Long> {

    List<SelectOption<String>> getCategories();

}
