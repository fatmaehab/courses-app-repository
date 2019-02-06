package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entities.Course;
import com.project.entities.Student;
import com.project.repositories.StudentRepository;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	public void saveStudent(Student student) {
		repository.save(student);
	}
	
	public void deleteStudent(Long id) {
		Student student = this.findStudent(id);
		repository.delete(student);
	}

	public Student findStudent(Long id) {
		return repository.findById(id).orElse(new Student());
	}

	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
		repository.findAll().forEach(students::add);
		
		students.forEach(System.out::println);
		
		return students;
	}
	
	public void registerCourses(Long studentId, List<Course> courses) {
		Student student = repository.findByIdAndFetchCoursesEagerly(studentId);
		if(student.getRegisteredCourses() == null ) {
			student.setRegisteredCourses(new ArrayList<>());
		}
		student.getRegisteredCourses().addAll(courses);
		saveStudent(student);
	}
}
