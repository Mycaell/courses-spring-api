package com.mycaell.crudspring.presentation.course.dto;

import com.mycaell.crudspring.domain.course.enums.Category;
import com.mycaell.crudspring.domain.course.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CourseRequest implements Serializable {

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    private String name;

    @NotNull
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CourseRequest{" +
                "name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
