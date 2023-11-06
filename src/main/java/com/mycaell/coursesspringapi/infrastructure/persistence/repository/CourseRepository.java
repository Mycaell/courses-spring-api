package com.mycaell.coursesspringapi.infrastructure.persistence.repository;

import com.mycaell.coursesspringapi.domain.course.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
