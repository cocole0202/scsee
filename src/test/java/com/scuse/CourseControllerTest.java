package com.scuse;

import com.scuse.entity.Course;
import com.scuse.service.CourseService;
import com.scuse.dto.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseControllerTest {
    @Autowired
    CourseService courseService;

    @Test
    public void addCourse() throws Exception {
        List<Course> courses= new ArrayList<Course>();
        Course course = new Course();
        course.setId(1);
        course.setName("Data System");
        courses.add(course);
        Result result=courseService.addCourse(courses);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void delMajor() throws Exception {
        List<Integer> ids=new ArrayList<Integer>();
        ids.add(11);
        Result result = courseService.delCourse(ids);
        System.out.println(result.getError());
    }

    @Test
    public void updMajor() throws Exception {
        List<Course> courses= new ArrayList<Course>();
        Course course=new Course();
        course.setId(1);
        course.setName("软件工程");
        courses.add(course);
        Result result=courseService.updCourse(courses);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void getMajor() throws Exception {
    }

}