package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Major;
import com.scuse.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sheng Junyi on 2018/3/20
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    MajorService majorService;

    /*
    * 添加Major
    * @param majors: 要添加的专业
    * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addMajor(@RequestBody List<Major> majors){return majorService.addMajor(majors);}

    /*
   * 删除Major
   * @param : 要删除的专业的id列表
   * @return 返回删除操作错误信息
    */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delMajor(@RequestBody List<Integer> ids){ return majorService.delMajor(ids);}

    /*
   * 更新Major
   * @param majors: majors列表
   * @return 返回更新操作错误信息
    */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updMajor(@RequestBody List<Major>majors){return majorService.updMajor(majors);}

    /*
   * 获取Major
   * @return 返回操作错误信息以及major列表
    */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getMajor(){return majorService.getMajors();}
}
