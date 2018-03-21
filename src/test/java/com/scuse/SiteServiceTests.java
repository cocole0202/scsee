package com.scuse;

import com.scuse.dto.Result;
import com.scuse.entity.Site;
import com.scuse.service.SiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteServiceTests {
    @Autowired
    SiteService siteService;
    /*
    * 测试GetAll方法
    */
    @Test
    public void testGet(){
        Result result = siteService.getSites();
        System.out.println(result.getError().getMessage());
    }
    /*
    * 添加site测试
    * */
    @Test
    public void testAdd(){
        List<Site> sites = new ArrayList<Site>();
        Site site = new Site();
        site.setId(500);
        site.setAddr("asdff");
        site.setCandLimit(123);
        sites.add(site);
        Result result = siteService.addSite(sites);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 通过id删除site测试
    * */
   @Test
    public void testDel(){
       int[] ids= new int[]{400,500};
        Result result = siteService.delSite(ids);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 更新site测试
    * */
     @Test
    public void testUpd(){
         List<Site> sites = new ArrayList<Site>();
         Site site = new Site();
         site.setId(10);
         site.setAddr("asdff");
         site.setCandLimit(123);
         sites.add(site);
         Result result = siteService.updSite(sites);
         System.out.println(result.getError().getMessage());
    }
}
