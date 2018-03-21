package com.scuse;

import com.scuse.dto.Result;
        import com.scuse.entity.Score;
        import com.scuse.service.ScoreService;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.util.ArrayList;
        import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreServiceTests {
    @Autowired
    ScoreService scoreService;
    /*
    * 测试GetAll方法
    */
    @Test
    public void testGet(){
        Result result = scoreService.getScores();
        System.out.println(result.getError().getMessage());
    }
    /*
    * 添加Score测试
    * */
    @Test
    public void testAdd(){
        List<Score> scores = new ArrayList<Score>();
        Score score = new Score();
        score.setCandId(500);
        score.setTickId(300);
        score.setScore(300);
        scores.add(score);
        Result result = scoreService.addScore(scores);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 通过cand_id删除score测试
    * */
    @Test
    public void testDel(){
        int[] cand_ids= new int[]{400,500};
        Result result = scoreService.delScores(cand_ids);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 更新score测试
    * */
    @Test
    public void testUpd(){
        List<Score> scores = new ArrayList<Score>();
        Score score = new Score();
        score.setCandId(500);
        score.setTickId(300);
        score.setScore(300);
        scores.add(score);
        Result result = scoreService.updScore(scores);
        System.out.println(result.getError().getMessage());
    }
}
