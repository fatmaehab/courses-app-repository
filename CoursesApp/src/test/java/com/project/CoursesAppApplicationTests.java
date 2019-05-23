package com.project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.entities.Course;
import com.project.services.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoursesAppApplicationTests {
	
	@Autowired
	CourseService service;

	@Test
	public void addCourse() {
		
		Course c = new Course();
		c.setTitle("Java");
		c.setDuration(4);
		c = service.addUpdateCourse(c);
		Assert.assertEquals(Long.valueOf(1), c.getId());
	}

}

