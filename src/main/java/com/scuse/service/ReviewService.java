package com.scuse.service;


import com.scuse.dto.*;
import com.scuse.dto.Error;
import com.scuse.entity.*;
import com.scuse.mapper.*;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("reviewService")
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private SiteMapper siteMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewPass(ReqReviewEntity reqReviewEntity){
        try {
            if (reqReviewEntity.getId() != null && reqReviewEntity.getType() != null) {
                Review review = new Review(reqReviewEntity.getId(), reqReviewEntity.getType());
                reviewMapper.delete(review);
                return OpResult.DEL_SUCCESS;
            } else
                return OpResult.DEL_ERROR;
        }catch (Exception e){
            e.printStackTrace();
            return OpResult.DEL_ERROR;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewAdd(ReqReviewEntity reqReviewEntity){
        try{
            Review review = new Review(reqReviewEntity.getId(),reqReviewEntity.getType());
            reviewMapper.insert(review);
            return new Result(new Error(0,"增加成功"),review);
        }catch (Exception e){
            e.printStackTrace();
            return OpResult.ADD_ERROR;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewGetAll(){
        try{
            List<Review> list = reviewMapper.getAll();
            return new Result(new Error(0,"获取成功"),list);
        }catch (Exception e){
            e.printStackTrace();
            return OpResult.GET_ERROR;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewGetById(int id){
        try{
            List<Review> list = reviewMapper.selectById(id);
            return new Result(new Error(0,"获取成功"),list);
        }catch(Exception e){
            e.printStackTrace();
            return OpResult.GET_ERROR;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewGetByType(int type){
        try{
            List<Review> list = reviewMapper.selectByType(type);
            return new Result(new Error(0,"获取成功"),list);
        }catch(Exception e){
            e.printStackTrace();
            return OpResult.GET_ERROR;
        }
    }

    /*
    GET方法，返回一个包含三个子对象的审核数据，分别为：考生信息修改，管理员信息修改，考生考试报名
     流程：
     1.从review表中获取所有考生列表(type=1 考生注册 || type=6 考生信息修改),将信息保存到List<ReviewReturnCandEntity> listCand中
     2.从review表中获取所有管理员列表(type=7 管理员注册 || type=8 管理员信息修改)，将信息保存到List<ReviewReturnAdminEntity> listAdmin中
     3.从review表中获取所有考生报名列表（type=2）获取考生id,
        根据考生id在candidate表中查找（name考生姓名, idNum考生身份证号）
        根据考生id在register表中查找对应的考试报名数据，每个考生可以报名多场考试，因此会有多条数据：
            根据register表中的考试计划ID，在考试计划表plan中查找（major专业, course课程, examTime开考时间）
            根据register表中的考点ID，在考点信息表site中查找（addr考场地址）
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewGetDetailInfo(){
        try {
            List<ReviewReturnCandEntity> listCand = new ArrayList<ReviewReturnCandEntity>();
            List<ReviewReturnAdminEntity> listAdmin = new ArrayList<ReviewReturnAdminEntity>();
            List<ReviewReturnRegEntity> listReg = new ArrayList<ReviewReturnRegEntity>();
            //1. listCand
            List<Review> candReviewList = reviewMapper.selectByTypeWithTwoTypes(1,6);
            for(Review review:candReviewList){
                if(review!=null) {
                    int id = review.getId();
                    //根据id查找考生信息
                    Candidate candidate = candidateMapper.selectById(id);
                    //新建ReviewReturnCandEntity对象保存数据
                    ReviewReturnCandEntity reviewReturnCandEntity = new ReviewReturnCandEntity(candidate);
                    listCand.add(reviewReturnCandEntity);
                }else
                    continue;;
            }

            //2. listAdmin
            List<Review> adminReviewList = reviewMapper.selectByTypeWithTwoTypes(7,8);
            for(Review review: adminReviewList){
                if(review!=null){
                    int id = review.getId();
                    //根据ID查找管理员信息
                    Admin admin = adminMapper.selectById(id);
                    //新建DTO对象保存数据
                    ReviewReturnAdminEntity reviewReturnAdminEntity = new ReviewReturnAdminEntity(admin);
                    listAdmin.add(reviewReturnAdminEntity);
                }else{
                    continue;
                }
            }

            //3. listReg
            List<Review> regReviewList = reviewMapper.selectByType(2); //获取所有type=2的考生列表,类别：考试报名
            for (Review aReview : regReviewList) { //对于每个考生
                if (aReview != null) {
                    System.out.println(aReview.getId());
                    int candId = aReview.getId();
                    //查询candidate表获取信息
                    Candidate candidate = candidateMapper.selectById(candId);
                    String candName = candidate.getName();
                    String candIdNum = candidate.getIdNum();
                    //查询register表中信息，使用List<Register>接收
                    List<Register> listReg_aCand = registerMapper.selectByCandId(candId);
                    //对于当前考生的每一项考试报名
                    for (Register register_aCand : listReg_aCand) {
                        if (register_aCand != null) {
                            int plnId = register_aCand.getPlnId(); //获取考试计划ID
                            int sitId = register_aCand.getSitId(); //获取考点ID
                            //1.考试计划ID
                            //根据考试计划ID在plan表中查找majorID, courseID, examTime
                            Plan plan = planMapper.selectByPrimaryKey(plnId);
                            int majorId = plan.getMjrId();
                            int courseId = plan.getCrsId();
                            Date examTime = plan.getExamTime();
                            //根据majorID，courseID查找名称majorName, courseName
                            Major major = majorMapper.selectByPrimaryKey(majorId);
                            Course course = courseMapper.selectByPrimaryKey(courseId);
                            String majorName = major.getName();
                            String courseName = course.getName();
                            //2.考点ID
                            //根据考点ID在site表中查找addr
                            Site site = siteMapper.selectByPrimaryKey(sitId);
                            String siteName = site.getAddr();
                            //新建ReviewReturnRegEntity接收信息
                            ReviewReturnRegEntity reviewReturnRegEntity = new ReviewReturnRegEntity(candName, majorName, courseName, siteName, examTime, candIdNum);
                            //将新建对象添加到listReg列表中
                            listReg.add(reviewReturnRegEntity);
                            System.out.println("对象添加成功。");
                        } else {
                            continue;
                        }
                    }

                }else{
                    continue;
                }
            }
            return new Result(new Error(0, "查询成功"), new ReviewGetEntity(listCand, listAdmin, listReg));
        }catch (Exception e){
            e.printStackTrace();
            return OpResult.GET_ERROR;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewUpd(ReviewGetEntity reviewGetEntity){
        try {
            List<ReviewReturnCandEntity> listCand = reviewGetEntity.getCands();
            List<ReviewReturnAdminEntity> listAdmin = reviewGetEntity.getAdmins();
            List<ReviewReturnRegEntity> listReg = reviewGetEntity.getRegs();

            //1.更新考生的review信息
            //传入参数列表是需要留下的，其他需要删除
            //先从数据库中获取所有type=1 || type=6的review列表，删除其中不在传入列表内的条目
            List<Review> listReviewDB = reviewMapper.selectByTypeWithTwoTypes(1, 6);
            for (Review review : listReviewDB) {
                if (review != null) {
                    //获取对应考生的身份证号，使用身份证号完成比对
                    Candidate candidate = candidateMapper.selectById(review.getId());
                    String idNum = candidate.getIdNum();
                    boolean willDelete = true;
                    for (ReviewReturnCandEntity reviewReturnCandEntity : listCand) {
                        if (reviewReturnCandEntity != null)
                            if (idNum.equals(reviewReturnCandEntity.getIdNum())) {
                                willDelete = false;
                            }
                    }
                    if (willDelete) { //删除该项
                        reviewMapper.delete(review);
                    }
                }
            }

            //2.更新管理员的review信息
            List<Review> listReviewDB_admin = reviewMapper.selectByTypeWithTwoTypes(7, 8);
            for (Review review : listReviewDB_admin) {
                if (review != null) {
                    //获取对应管理员的身份证号,使用身份证号完成比对
                    Admin admin = adminMapper.selectById(review.getId());
                    String idNum = admin.getIdNum();
                    boolean willDelete = true;
                    for (ReviewReturnAdminEntity reviewReturnAdminEntity : listAdmin) {
                        if (reviewReturnAdminEntity != null) {
                            if (idNum.equals(reviewReturnAdminEntity.getIdNum()))
                                willDelete = false;
                        }
                    }
                    if (willDelete)
                        reviewMapper.delete(review);
                }
            }

            //3.更新考生报考的review信息
            //通过idNum获取考生id，从数据库中删除Review(id,2)
            //先从数据库中获取所有type=2的review列表
            List<Review> listReviewDB_reg = reviewMapper.selectByType(2);
            for(Review review:listReviewDB_reg){
                if(review!=null){
                    //获取对应考生身份证号
                    Candidate candidate = candidateMapper.selectById(review.getId());
                    String idNum = candidate.getIdNum();
                    boolean willDelete = true;
                    //对于数据库中每一项，遍历搜索输入列表中是否存在
                    for(ReviewReturnRegEntity reviewReturnRegEntity: listReg){
                        if(reviewReturnRegEntity!=null){
                            if(idNum.equals(reviewReturnRegEntity.getIdNum()))
                                willDelete = false;
                        }
                    }
                    if(willDelete)
                        reviewMapper.delete(review);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return OpResult.UPD_ERROR;
        }
        return OpResult.UPD_SUCCESS;
    }
}
