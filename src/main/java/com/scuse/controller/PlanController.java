package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Plan;
import com.scuse.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    PlanService planService;

    /*
     * 添加Plan
     * @param plans: 要添加的考次
     * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addPlan(@RequestBody List<Plan> plans) {
        return planService.addPlan(plans);
    }

    /*
     * 删除Plan
     * @param ids: 需要删除的plan的id列表
     * @return 返回删除操作错误信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delPlan(@RequestBody int[] ids) {
        return planService.delPlan(ids);
    }

    /*
     * 更新Plan
     * @param plans: plan列表
     * @return 返回更新操作错误信息
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updPlan(@RequestBody List<Plan> plans) {
        return planService.updPlan(plans);
    }


    /*
     * 获取Plan
     * @return 返回操作错误信息以及plan列表
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result updPlan() {
        return planService.getPlan();
    }
}
