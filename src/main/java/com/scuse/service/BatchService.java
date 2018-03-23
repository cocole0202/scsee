package com.scuse.service;

import java.util.ArrayList;
import java.util.List;

import com.scuse.dto.*;
import com.scuse.dto.Error;
import com.scuse.entity.*;
import com.scuse.mapper.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jaho on 2018/3/20.
 *
 * Batch 相关跨表操作
 */

@Repository
public class BatchService {

     @Autowired
     BatchMapper batchMapper;
     @Autowired
     PlanMapper planMapper;
     @Autowired
     MajorMapper majorMapper;
     @Autowired
     CourseMapper courseMapper;
     @Autowired
    SiteMapper siteMapper;
     @Autowired
     PlanSiteMapper planSiteMapper;
    /*
     * 添加Batch
     * @param batches: batch列表
     * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result addBatch(List<Batch> batches) {
        try{
            for (Batch batch:batches) {
                batchMapper.insert(batch);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
     * 删除Batch
     * @param ids: 需要删除的batch的id列表
     * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delBatch(List<Integer> ids) {
        try{
            for (Integer id:ids) {
                batchMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Batch
     * @param batches: batch列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updBatch(List<Batch> batches) {
        try{
            for (Batch batch:batches) {
                batchMapper.updateByPrimaryKeySelective(batch);
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取Batch
     * @return 返回操作错误信息以及Batch列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getBatches() {
        try{
            List<BatchGetEntity> batchGetList = new ArrayList<BatchGetEntity>();

            //从batch中获取所有考次
            List<Batch> listBatchDB = batchMapper.getAll();
            //对每个考次处理
            for(Batch batch: listBatchDB){
                if(batch!=null){
                    System.out.println("batch:"+batch.getId());
                    //存储该考次的信息
                    BatchGetEntity batchGetEntity = new BatchGetEntity();
                    batchGetEntity.setExamTime(batch.getExamTime());
                    batchGetEntity.setCandLimit(batch.getCandLimit());
                    //新建该考次的考试计划列表
                    List<BatchPlanEntity> batchPlanEntityList = new ArrayList<BatchPlanEntity>();
                    //从考试计划中获取当前考次对应的考试计划
                    List<Plan> listPlanDB = planMapper.selectByBatchId(batch.getId());
                    //对于每个考试计划，新建实体并存储相关信息到batchPlanList中
                    for(Plan plan: listPlanDB){
                        if(plan!=null){
                            System.out.println("plan:"+plan.getId());
                            BatchPlanEntity batchPlanEntity = new BatchPlanEntity();
                            //存储major,course,examTime,candLimit,hours信息
                            //其中major，course需要从相应表中查找名称
                            Major major = majorMapper.selectByPrimaryKey(plan.getMjrId());
                            String majorName = major.getName();
                            Course course = courseMapper.selectByPrimaryKey(plan.getCrsId());
                            String courseName = course.getName();
                            batchPlanEntity.setMajor(majorName);
                            batchPlanEntity.setCourse(courseName);
                            batchPlanEntity.setExamTime(plan.getExamTime());
                            batchPlanEntity.setCandLimit(batch.getCandLimit());
                            try{
                                batchPlanEntity.setHours(plan.getHours());
                            }catch(Exception e){
                                batchPlanEntity.setHours(0);
                            }

                            //获取考点列表
                            List<BatchSiteEntity> batchSiteEntityList = new ArrayList<BatchSiteEntity>();
                            //从考试计划-考点关系表中获取所有该考试计划的考点
                            List<PlanSite> planSiteList = planSiteMapper.selectByPlanId(plan.getId());
                            //对于该关系表中的每项，根据考点id从考点表中获取考点name
                            for(PlanSite planSite: planSiteList){
                                if(planSite!=null){
                                    Site site = siteMapper.selectByPrimaryKey(planSite.getSiteId());
                                    String siteName = site.getAddr();
                                    BatchSiteEntity batchSiteEntity = new BatchSiteEntity();
                                    batchSiteEntity.setAddr(siteName);
                                    batchSiteEntity.setCandLimit(site.getCandLimit());
                                    //添加考点信息到考点列表中
                                    batchSiteEntityList.add(batchSiteEntity);
                                }
                            }
                            //获取考点列表完成后将其添加到对应考试计划项中
                            batchPlanEntity.setListSite(batchSiteEntityList);
                            //添加到列表中
                            batchPlanEntityList.add(batchPlanEntity);
                        }
                    }
                    //将当前考次的考试计划列表添加进去
                    batchGetEntity.setListPlan(batchPlanEntityList);
                    //将当前考次添加到考次列表中
                    batchGetList.add(batchGetEntity);
                }
            }
            return new Result(new Error(0,"获取成功"),batchGetList);
        }catch (Exception e) {
            e.printStackTrace();
            return OpResult.GET_ERROR;
        }
    }
}
