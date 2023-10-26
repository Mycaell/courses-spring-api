package com.mycaell.crudspring.presentation.course.dto;

import com.mycaell.crudspring.domain.course.enums.Category;
import com.mycaell.crudspring.domain.course.enums.Status;

public record CourseResponse(
        Long id,
        String name,
        Category category,
        Status status
) {
}
