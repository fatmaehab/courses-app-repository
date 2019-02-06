package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Course;
import com.project.services.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@RequestMapping(value="/course", method=RequestMethod.POST)
	public void addCourse(@RequestBody Course course) {
		service.addUpdateCourse(course);
	}
	
	@RequestMapping(value="/course", method=RequestMethod.PUT)
	public void updateCourse(@RequestBody Course course) {
		service.addUpdateCourse(course);
	}
	
	@RequestMapping(value = "/course/{id}", method=RequestMethod.DELETE)
	public void deleteCourse(@PathVariable Long id) {
		service.deleteCourse(id);
	}
	
	@RequestMapping("/course/{id}")
	public Course getCourse(@PathVariable Long id) {
		return service.findCourse(id);
	}
	
	@RequestMapping(value="/course")
	public List<Course> getAllCourses() {
		
		return service.getCourses();
	}
}
