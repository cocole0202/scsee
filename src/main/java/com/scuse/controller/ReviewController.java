package com.scuse.controller;


import com.scuse.dto.ReqReviewEntity;
import com.scuse.dto.Result;
import com.scuse.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/pass",method = RequestMethod.POST)
    public Result reviewPass(@RequestBody ReqReviewEntity reqReviewEntity){
        return reviewService.reviewPass(reqReviewEntity);
    }
}
