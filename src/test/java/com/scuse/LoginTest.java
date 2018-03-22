package com.scuse;

import com.scuse.dto.OpResult;
import com.scuse.dto.ReqLoginUser;
import com.scuse.dto.Result;
import com.scuse.service.LoginService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
本部分测试内容如下：
1.身份证号登录
	是管理员，通过审核，密码正确
	是管理员，通过审核，密码错误
	是管理员，未通过审核
	是考生，通过审核，密码正确
	是考生，通过审核，密码错误
	是考生，未通过审核
	用户不存在
2.手机号登录
	是管理员，通过审核，密码正确
	是管理员，通过审核，密码错误
	是管理员，未通过审核
	是考生，通过审核，密码正确
	是考生，通过审核，密码错误
	是考生，未通过审核
	用户不存在
3.邮箱登录
	是管理员，通过审核，密码正确
	是管理员，通过审核，密码错误
	是管理员，未通过审核
	是考生，通过审核，密码正确
	是考生，通过审核，密码错误
	是考生，未通过审核
	用户不存在
4.信息不完整
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {

    @Autowired
    LoginService loginService;

    //测试数据说明：
    //数据库中admin表新建两条记录：通过审核、未通过审核（未通过审核即在review表中有记录，其中，type=1是考生审核，type=7是管理员审核
    //数据库中candidate表中新建两条记录，同上。
    //一共四条记录，为测试方便，所有记录的密码全部设置为123456
    //测试用错误密码为000000
    private String IdNum_adminReviewed = "610236199702026487";
    private String IdNum_adminNotReviewed = "610135199702026198";
    private String IdNum_candReviewed = "610104199702029746";
    private String IdNum_candNotReviewed = "564123199702026985";
    private String IdNum_empty = "1111111";

    private String phone_adminReviewed = "17713415894";
    private String phone_adminNotReviewed = "17796854263";
    private String phone_candReviewed = "13572223535";
    private String phone_candNotReviewed = "13589964625";
    private String phone_empty = "2222222";

    private String mail_adminReviewed = "cocole0202@163.com";
    private String mail_adminNotReviewed = "cocole0202@132.com";
    private String mail_candReviewed = "787559181@qq.com";
    private String mail_candNotReviewed = "787559182@qq.com";
    private String mail_empty = "33333333";

    private String rightPass = "123456";
    private String wrongPass = "000000";

    ////////////////////////////使用身份证登录//////////////////////////////
    /*
    使用身份证登录，是管理员，通过审核，密码正确.
     */
    @Test
    public void LoginWithIdNum1(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_adminReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        Assert.assertEquals(result.getError().getCode(),0);
    }

    /*
    使用身份证登录，是管理员，通过审核，密码错误.
     */
    @Test
    public void LoginWithIdNum2(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_adminReviewed);
        reqLoginUser.setPassword(wrongPass);
        Result result = loginService.login(reqLoginUser);
        Assert.assertEquals((OpResult) result, OpResult.WRONG_PASS_ERROR);
    }

    /*
    使用身份证登录，是管理员，未通过审核
     */
    @Test
    public void LoginWithIdNum3(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_adminNotReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.LOGIN_REVIEW_ERROR);
    }

    /*
    使用身份证登录，是考生，通过审核，密码正确
     */
    @Test
    public void LoginWithIdNum4(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_candReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals(result.getError().getCode(),0);
    }

    /*
    使用身份证登录，是考生，通过审核，密码错误
     */
    @Test
    public void LoginWithIdNum5(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_candReviewed);
        reqLoginUser.setPassword(wrongPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.WRONG_PASS_ERROR);
    }

    /*
    使用身份证登录，是考生，未通过审核
     */
    @Test
    public void LoginWithIdNum6(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_candNotReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.LOGIN_REVIEW_ERROR);
    }

    /*
    使用身份证登录，用户不存在
     */
    @Test
    public void LoginWithIdNum7(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setIdNum(IdNum_empty);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.LOGIN_EMPTY_ERROR);
    }

    ////////////////////////////使用手机号登录//////////////////////////////
    /*
    使用手机号登录，是管理员，通过审核，密码正确.
     */
    @Test
    public void LoginWithPhone1(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_adminReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals(result.getError().getCode(),0);
    }

    /*
    使用手机号登录，是管理员，通过审核，密码错误.
     */
    @Test
    public void LoginWithPhone2(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_adminReviewed);
        reqLoginUser.setPassword(wrongPass);
        Result result = loginService.login(reqLoginUser);
        Assert.assertEquals((OpResult) result, OpResult.WRONG_PASS_ERROR);
    }

    /*
    使用手机号登录，是管理员，未通过审核
     */
    @Test
    public void LoginWithPhone3(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_adminNotReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.LOGIN_REVIEW_ERROR);
    }

    /*
    使用手机号登录，是考生，通过审核，密码正确
     */
    @Test
    public void LoginWithPhone4(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_candReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals(result.getError().getCode(),0);
    }

    /*
    使用手机号登录，是考生，通过审核，密码错误
     */
    @Test
    public void LoginWithPhone5(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_candReviewed);
        reqLoginUser.setPassword(wrongPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.WRONG_PASS_ERROR);
    }

    /*
    使用手机号登录，是考生，未通过审核
     */
    @Test
    public void LoginWithPhone6(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_candNotReviewed);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.LOGIN_REVIEW_ERROR);
    }

    /*
    使用手机号登录，用户不存在
     */
    @Test
    public void LoginWithPhone7(){
        ReqLoginUser reqLoginUser = new ReqLoginUser();
        reqLoginUser.setPhone(phone_empty);
        reqLoginUser.setPassword(rightPass);
        Result result = loginService.login(reqLoginUser);
        System.out.println(result.getError().getMessage());
        Assert.assertEquals((OpResult) result, OpResult.LOGIN_EMPTY_ERROR);
    }

}
