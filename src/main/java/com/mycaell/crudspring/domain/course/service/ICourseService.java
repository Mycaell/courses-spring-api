package com.mycaell.crudspring.domain.course.service;

import com.mycaell.crudspring.domain.course.model.Course;
import com.mycaell.crudspring.domain.shared.service.CrudService;
import com.mycaell.crudspring.presentation.enums.dto.SelectOption;

import java.util.List;

public interface ICourseService extends CrudService<Course, Long> {

    List<SelectOption<String>> getCategories();

}
