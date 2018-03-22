package com.scuse.service;


import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Course;
import com.scuse.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sheng Junyi on2018/03/21
 *
 * Course表相关操作
 */
@Repository
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    /*
    * 添加course
    *  @param courses:course列表
    *  @return 返回添加操作错误信息
     */
    public Result addCourse(List<Course> courses){
        try{
            for(Course course: courses){
                //对于每个major，查询在数据库中是否有此项数据
                Course findMajor = courseMapper.selectByPrimaryKey(course.getId());
                if(findMajor!=null){
                    //执行更新操作
                    courseMapper.updateByPrimaryKeySelective(course);
                }
                else{
                    //执行插入操作
                    courseMapper.insertSelective(course);
                }
            }
        }catch(Exception e){
            return OpResult.ADD_ERROR;
        }
        return  OpResult.ADD_UPD_SUCCESS;
    }

    /*
    * 删除course
    *  @param courses：course的id列表
    *  @return 返回删除操作的相关信息
     */
    public Result delCourse(List<Integer> ids){
        try {
            for (Integer id:ids){
                courseMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e){
            return OpResult.DEL_ERROR;
        }
        return OpResult.DEL_SUCCESS;
    }

    /*
    * 更新course
    * @param courses：course列表
    * @return 返回更新操作的相关信息
     */
    public Result updCourse(List<Course> courses){
        try{
            //先进行添加和更新操作，直接调用addMajor函数
            addCourse(courses);
            //获取数据库中所有专业
            List<Course> allCourse_DB = courseMapper.getAll();
            //比较数据库和传入列表，删除传入列表中没有的专业
            for(Course course_DB: allCourse_DB){
                boolean exist = false;
                //在传入列表中查找
                for(Course course_in: courses){
                    if(course_DB.getId()==course_in.getId()){
                        //该条目存在,不需要删除
                        exist = true;
                        break;
                    }
                }
                if(exist==false){
                    //该条目不存在，从数据库中删除
                    courseMapper.deleteByPrimaryKey(course_DB.getId());
                }
            }
        }catch (Exception e){
            return  OpResult.UPD_ERROR;
        }
        return OpResult.UPD_SUCCESS;
    }

    /*
    * 获取course
    * @param courses:course列表
    * @return 返回获取操作的相关信息
     */
    public Result getCourse(){
        try {
            List<Course> courses=courseMapper.getAll();
            Result<List<Course>> result = new Result<>();
            result.setError(new Error(0,"获取课程信息成功"));
            result.setData(courses);
            return result;
        }catch (Exception e){
            return OpResult.GET_ERROR;
        }

    }
}
