package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Violation;
import com.scuse.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/violation")
public class ViolationController {

    @Autowired
    ViolationService violationService;

    /*
     * 添加Violation
     * @param violations: 要添加的考次
     * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addViolation(@RequestBody List<Violation> violations) {
        return violationService.addViolation(violations);
    }

    /*
     * 删除Violation
     * @param cand_ids: 需要删除的violation的id列表
     * @return 返回删除操作错误信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delViolation(@RequestBody int[] cand_ids) {
        return violationService.delViolation(cand_ids);
    }

    /*
     * 更新Violation
     * @param violations: violation列表
     * @return 返回更新操作错误信息
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updViolation(@RequestBody List<Violation> violations) {
        return violationService.updViolation(violations);
    }


    /*
     * 获取Violation
     * @return 返回操作错误信息以及Violation列表
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result updViolation() {
        return violationService.getViolation();
    }
}
