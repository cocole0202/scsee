package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Batch;
import com.scuse.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jaho on 2018/3/20.
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    BatchService batchService;

    /*
     * 添加Batch
     * @param batches: 要添加的考次
     * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addBatch(@RequestBody List<Batch> batches) {
        return batchService.addBatch(batches);
    }

    /*
     * 删除Batch
     * @param ids: 需要删除的batch的id列表
     * @return 返回删除操作错误信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delBatch(@RequestBody List<Integer> ids) {
        return batchService.delBatch(ids);
    }

    /*
     * 更新Batch
     * @param batches: batch列表
     * @return 返回更新操作错误信息
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updBatch(@RequestBody List<Batch> batches) {
        return batchService.updBatch(batches);
    }


    /*
     * 获取Batch
     * @return 返回操作错误信息以及Batch列表
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getBatch() {
        return batchService.getBatches();
    }
}
