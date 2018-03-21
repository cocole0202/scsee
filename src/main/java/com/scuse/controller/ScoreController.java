package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Score;
import com.scuse.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    /*
     * 添加Score
     * @param scores: 要添加的考次
     * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addScore(@RequestBody List<Score> scores) {
        return scoreService.addScore(scores);
    }

    /*
     * 删除Score
     * @param ids: 需要删除的score的id列表
     * @return 返回删除操作错误信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delScore(@RequestBody int[] ids) {
        return scoreService.delScores(ids);
    }

    /*
     * 更新Score
     * @param scores: score列表
     * @return 返回更新操作错误信息
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updScore(@RequestBody List<Score> scores) {
        return scoreService.updScore(scores);
    }


    /*
     * 获取Score
     * @return 返回操作错误信息以及Score列表
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result updScore() {
        return scoreService.getScores();
    }
}
