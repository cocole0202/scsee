package com.scuse.service;


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
import org.springframework.web.bind.annotation.RequestBody;

@Service("reviewService")
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result reviewPass(@RequestBody ReqReviewEntity reqReviewEntity){
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
}
