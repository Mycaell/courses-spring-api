package com.mycaell.crudspring.infrastructure.persistence.repository;

import com.mycaell.crudspring.domain.course.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
