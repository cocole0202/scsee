package com.scuse.service;


import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.ReqReviewEntity;
import com.scuse.dto.Result;
import com.scuse.entity.Review;
import com.scuse.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service("reviewService")
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

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
}
