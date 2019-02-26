package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Course;
import com.project.entities.Student;
import com.project.services.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@RequestMapping(value="/students", method=RequestMethod.POST)
	public void addStudent(@RequestBody Student student) {
		service.saveStudent(student);
	}
	
	@RequestMapping(value="/students", method=RequestMethod.PUT)
	public void updateStudent(@RequestBody Student student) {
		service.saveStudent(student);
	}
	
	@RequestMapping(value = "/students/{id}", method=RequestMethod.DELETE)
	public void deleteStudent(@PathVariable long id) {
		service.deleteStudent(id);
	}
	
	@RequestMapping("/students/{id}")
	public Student getStudent(@PathVariable long id) {
		return service.findStudent(id);
	}
	
	@RequestMapping(value="/students")
	public List<Student> getAllStudents() {
		
		return service.getStudents();
	}
	
	@RequestMapping(value="/students/{id}/register", method=RequestMethod.POST)
	public void register(@RequestBody List<Course> courses, @PathVariable Long id) {
		service.registerCourses(id, courses);
	}

}
