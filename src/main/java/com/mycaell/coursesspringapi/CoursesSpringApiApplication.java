package com.mycaell.coursesspringapi;

import com.mycaell.coursesspringapi.domain.course.enums.Category;
import com.mycaell.coursesspringapi.domain.course.enums.Status;
import com.mycaell.coursesspringapi.domain.course.model.Course;
import com.mycaell.coursesspringapi.infrastructure.persistence.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CoursesSpringApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursesSpringApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();

            Course c = new Course();
            c.setName("Angular com Spring");
            c.setCategory(Category.FRONT_END);
            c.setStatus(Status.ACTIVE);

            courseRepository.save(c);
        };
    }
}
