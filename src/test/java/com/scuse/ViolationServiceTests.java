package com.scuse;

import com.scuse.dto.Result;
import com.scuse.entity.Violation;
import com.scuse.service.ViolationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ViolationServiceTests {
    @Autowired
    ViolationService violationService;
    /*
    * 测试GetAll方法
    */
    @Test
    public void testGet(){
        Result result = violationService.getViolation();
        System.out.println(result.getError().getMessage());
    }
    /*
    * 添加violation测试
    * */
    @Test
    public void testAdd(){
        List<Violation> violations = new ArrayList<Violation>();
        Violation violation = new Violation();
        violation.setCandId(10);
        violation.setTickId(20);
        violation.setDegree(30);
        violation.setRoomId(40);
        violations.add(violation);
        Result result = violationService.addViolation(violations);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 通过id删除violation测试
    * */
    @Test
    public void testDel(){
        int[] cand_ids= new int[]{200,300};
        Result result = violationService.delViolation(cand_ids);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 更新violation测试
    * */
    @Test
    public void testUpd(){
        List<Violation> violations = new ArrayList<Violation>();
        Violation violation = new Violation();
        violation.setCandId(300);
        violation.setTickId(400);
        violation.setDegree(300);
        violation.setRoomId(300);
        violations.add(violation);
        Result result = violationService.updViolation(violations);
        System.out.println(result.getError().getMessage());
    }
}
