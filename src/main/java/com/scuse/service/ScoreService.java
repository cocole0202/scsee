package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Score;
import com.scuse.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    /*
     * 添加Score
     * @param scores: score列表
     * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public
    Result addScore(List<Score> scores) {
        try{
            for (Score score:scores) {
                scoreMapper.insert(score);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
     * 删除Score
     * @param ids: 需要删除的score的id列表
     * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delScores(int[] cand_ids) {
        try{
            for (Integer cand_id:cand_ids) {
                scoreMapper.deleteByPrimaryKey(cand_id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Score
     * @param scores: score列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updScore(List<Score> scores) {
        try{
            for (Score score:scores) {
                scoreMapper.updateByPrimaryKeySelective(score);
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取score
     * @return 返回操作错误信息以及Score列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getScores() {
        try{
            List<Score> scores = scoreMapper.getAll();
            Result<List<Score>> result = new Result<>();
            result.setError(new Error(0, "获取分数信息成功"));
            result.setData(scores);
            return result;
        }catch (Exception e) {
            return OpResult.GET_ERROR;
        }
    }
}
