package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Course;
import com.scuse.mapper.CourseMapper;
import com.scuse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sheng Junyi on2018/03/21
 */

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    /*
    * 添加的课程
    * @param courses：要添加的课程
    * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addCourse(@RequestBody List<Course> courses){return courseService.addCourse(courses);}

    /*
   * 删除课程
   * @param ids:course的课程id列表
   * @return 返回删除操作错误信息
    */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delMajor(@RequestBody List<Integer> ids){ return courseService.delCourse(ids);}

    /*
   * 更新Major
   * @param majors: majors列表
   * @return 返回更新操作错误信息
    */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updMajor(@RequestBody List<Course>courses){return courseService.updCourse(courses);}

    /*
   * 获取Major
   * @return 返回操作错误信息以及major列表
    */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getMajor(){return courseService.getCourse();}
}
