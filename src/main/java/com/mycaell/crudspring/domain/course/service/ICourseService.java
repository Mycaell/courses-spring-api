package com.mycaell.crudspring.domain.course.service;

import com.mycaell.crudspring.domain.course.model.Course;
import com.mycaell.crudspring.domain.shared.service.CrudService;

import java.util.List;

public interface ICourseService extends CrudService<Course, Long> {

    List<String> getCategories();

}
