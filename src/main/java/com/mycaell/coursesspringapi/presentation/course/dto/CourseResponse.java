package com.mycaell.coursesspringapi.presentation.course.dto;

import com.mycaell.coursesspringapi.domain.course.enums.Category;
import com.mycaell.coursesspringapi.domain.course.enums.Status;

import java.io.Serializable;

public class CourseResponse implements Serializable {

    private Long id;
    private String name;
    private Category category;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", status=" + status +
                '}';
    }
}
