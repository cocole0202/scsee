package com.scuse;

import com.scuse.dto.Result;
import com.scuse.entity.Plan;
import com.scuse.service.PlanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanServiceTests {
    @Autowired
    PlanService planService;
    /*
   * 测试GetAll方法
   */
    @Test
    public void testGet(){
        Result result = planService.getPlan();
        System.out.println(result.getError().getMessage());
    }
    /*
   * 添加Plan测试
   * */
    @Test
    public void testAdd(){
        List<Plan> plans = new ArrayList<Plan>();
        Plan plan = new Plan();

        plan.setId(20);
        plan.setBatId(100);
        plan.setMjrId(100);
        plan.setCrsId(100);
        String sDt = "08/31/2006 21:08:00";
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date dt2 = sdf.parse(sDt);
            plan.setExamTime(dt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        plan.setHours(1.5f);
        plans.add(plan);
        Result result = planService.addPlan(plans);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 通过id删除plan测试
    * */
    @Test
    public void testDel(){
        int[] ids= new int[]{20,2};
        Result result = planService.delPlan(ids);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 更新plan测试
    * */
    @Test
    public void testUpd(){
        List<Plan> plans = new ArrayList<Plan>();
        Plan plan = new Plan();

        plan.setId(20);
        plan.setBatId(100);
        plan.setMjrId(100);
        plan.setCrsId(100);
        String sDt = "08/31/2007 21:08:00";
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date dt2 = sdf.parse(sDt);
            plan.setExamTime(dt2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        plan.setHours(1.5f);
        plans.add(plan);
        Result result = planService.updPlan(plans);
        System.out.println(result.getError().getMessage());
    }
}
