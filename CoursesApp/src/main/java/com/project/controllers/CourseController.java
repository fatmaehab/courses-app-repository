package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Course;
import com.project.services.CourseService;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@RequestMapping(value="/courses", method=RequestMethod.POST)
	public void addCourse(@RequestBody Course course) {
		service.addUpdateCourse(course);
	}
	
	@RequestMapping(value="/courses", method=RequestMethod.PUT)
	public Course updateCourse(@RequestBody Course course) {
		return service.addUpdateCourse(course);
	}
	
	@RequestMapping(value = "/courses/{id}", method=RequestMethod.DELETE)
	public void deleteCourse(@PathVariable Long id) {
		service.deleteCourse(id);
	}
	
	@RequestMapping("/courses/{id}")
	public Course getCourse(@PathVariable Long id) {
		return service.findCourse(id);
	}
	
	@RequestMapping(value="/courses")
	public List<Course> getAllCourses() {
		
		return service.getCourses();
	}
}
