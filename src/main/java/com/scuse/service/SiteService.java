package com.scuse.service;

import java.util.List;
import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Site;
import com.scuse.mapper.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * Site 相关跨表操作
 */

@Repository
public class SiteService {

    @Autowired
    SiteMapper siteMapper;

    /*
    * 添加site
    * @param sites: site列表
    * @return 返回添加操作错误信息
    */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public
    Result addSite(List<Site> sites) {
        try{
            for (Site site :sites) {
                siteMapper.insert(site);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
        * 删除Site
        * @param ids: 需要删除的site的id列表
        * @return 返回删除操作错误信息
        */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delSite(int[] ids) {
        try{
            for (Integer id:ids) {
                siteMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Site
     * @param sites: sites列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updSite(List<Site> sites) {
        try{
            for (Site site:sites) {
                siteMapper.updateByPrimaryKeySelective(site);
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取Site
     * @return 返回操作错误信息以及Site列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getSites() {
        try{
            List<Site> sites = siteMapper.getAll();
            Result<List<Site>> result = new Result<>();
            result.setError(new Error(0, "获取考场信息成功"));
            result.setData(sites);
            return result;
        }catch (Exception e) {
            return OpResult.GET_ERROR;
        }
    }
}
