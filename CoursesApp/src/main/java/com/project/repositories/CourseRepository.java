package com.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
