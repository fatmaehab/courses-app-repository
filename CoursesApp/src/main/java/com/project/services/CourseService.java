package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entities.Course;
import com.project.repositories.CourseRepository;

@Transactional
@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repository;
	
	public Course addUpdateCourse(Course course) {
		return repository.save(course);
	}
	
	public void deleteCourse(Long id) {
		Course course = this.findCourse(id);
		repository.delete(course);
	}

	public Course findCourse(Long id) {
		return repository.findById(id).get();
	}

	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<Course>();
		repository.findAll().forEach(courses::add);
		
		courses.forEach(System.out::println);
		
		return courses;
	}
}
