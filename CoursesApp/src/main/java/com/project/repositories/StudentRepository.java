package com.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	
	@Query("SELECT s FROM Student s LEFT JOIN FETCH s.registeredCourses WHERE s.id = (:id)")
    public Student findByIdAndFetchCoursesEagerly(@Param("id") Long id);

}
