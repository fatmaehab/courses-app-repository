package com.project.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student {
	
	@Id
	private Long id;
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.ALL
            })
	private List<Course> registeredCourses = new ArrayList<Course>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	

}
