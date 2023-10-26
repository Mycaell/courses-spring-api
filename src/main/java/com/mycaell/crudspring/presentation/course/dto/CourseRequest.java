package com.mycaell.crudspring.presentation.course.dto;

import com.mycaell.crudspring.domain.course.enums.Category;
import com.mycaell.crudspring.domain.course.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public record CourseRequest(
        @NotBlank @NotNull @Length(min = 5, max = 100) String name,
        @NotNull Category category,
        @NotNull Status status) {
}
