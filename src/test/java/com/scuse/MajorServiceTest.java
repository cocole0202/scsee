package com.scuse;

import com.scuse.entity.Major;
import com.scuse.service.MajorService;
import com.scuse.dto.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorServiceTest {
    @Autowired
    MajorService majorService;

    @Test
    public void addMajor() {
        List<Major>majors= new ArrayList<Major>();
        Major major = new Major();
        major.setId(11);
        major.setCode("12");
        majors.add(major);
        Result result=majorService.addMajor(majors);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void delMajor() throws Exception {
        List<Integer> ids= new ArrayList<Integer>();
        ids.add(11);
        Result result=majorService.delMajor(ids);
        System.out.println(result.getError().getMessage());
    }

    @Test
    public void updMajor()  {
        List<Major> majors= new ArrayList<Major>();
        Major major=new Major();
        major.setId(11);
        major.setCode("251");
        majors.add(major);
        Result result=majorService.updMajor(majors);
        System.out.println(result.getError().getMessage());
    }


    @Test
    public void getMajor(){
        Result result =majorService.getMajors();
        System.out.println(result.getError().getMessage());
    }

}