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

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public Result reviewPass(@RequestBody ReqReviewEntity reqReviewEntity){
        return reviewService.reviewPass(reqReviewEntity);
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Result reviewAdd(@RequestBody ReqReviewEntity reqReviewEntity){
        return reviewService.reviewAdd(reqReviewEntity);
    }

    @RequestMapping(value="/get/all",method = RequestMethod.GET)
    public Result reviewGetAll(){
        return reviewService.reviewGetAll();
    }

    @RequestMapping(value="/get/id/{id}",method=RequestMethod.GET)
    public Result reviewGetById(@PathVariable("id") int id){
        return reviewService.reviewGetById(id);
    }

    @RequestMapping(value="/get/type/{type}",method=RequestMethod.GET)
    public Result reviewGetByType(@PathVariable("type") int type){
        return reviewService.reviewGetByType(type);
    }
}
