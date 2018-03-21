package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Site;
import com.scuse.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    SiteService siteService;

    /*
     * 添加Site
     * @param sites: 要添加的考次
     * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addSite(@RequestBody List<Site> sites) {
        return siteService.addSite(sites);
    }

    /*
     * 删除Site
     * @param ids: 需要删除的site的id列表
     * @return 返回删除操作错误信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delSite(@RequestBody int[] ids) { return siteService.delSite(ids);}

    /*
     * 更新Site
     * @param sites: site列表
     * @return 返回更新操作错误信息
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updSite(@RequestBody List<Site> sites) {
        return siteService.updSite(sites);
    }


    /*
     * 获取Site
     * @return 返回操作错误信息以及Site列表
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result updSite() {
        return siteService.getSites();
    }
}
