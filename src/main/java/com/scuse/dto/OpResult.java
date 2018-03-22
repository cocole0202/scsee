package com.scuse.dto;

/**
 * Created by Jaho on 2018/3/20.
 *
 * 增删改查操作返回结果
 */
public class OpResult extends Result<Object> {

    public static final OpResult ADD_SUCCESS = new OpResult(new Error(0,"添加成功"));
    public static final OpResult ADD_UPD_SUCCESS = new OpResult(new Error(0,"添加并更新成功"));
    public static final OpResult DEL_SUCCESS = new OpResult(new Error(0,"删除成功"));
    public static final OpResult UPD_SUCCESS = new OpResult(new Error(0,"更新成功"));
    public static final OpResult LOGIN_SUCCESS = new OpResult(new Error(0,"登录成功"));
    public static final OpResult REG_SUCCESS = new OpResult(new Error(0,"注册成功"));

    public static final OpResult ADD_ERROR = new OpResult(new Error(1,"添加失败"));
    public static final OpResult DEL_ERROR = new OpResult(new Error(1,"删除失败"));
    public static final OpResult UPD_ERROR = new OpResult(new Error(1,"更新失败"));
    public static final OpResult GET_ERROR = new OpResult(new Error(1,"信息查询失败"));
    public static final OpResult REG_ERROR = new OpResult(new Error(1,"注册失败"));

    public static final OpResult LOGIN_ERROR = new OpResult(new Error(1,"登录信息不完整"));
    public static final OpResult LOGIN_EMPTY_ERROR = new OpResult(new Error(1,"账户不存在"));
    public static final OpResult LOGIN_REVIEW_ERROR = new OpResult(new Error(1,"未通过审核"));
    public static final OpResult WRONG_PASS_ERROR = new OpResult(new Error(1,"密码错误"));

    public static final OpResult REG_EXIST_ERROR = new OpResult(new Error(1,"用户已存在"));
    public static final OpResult REG_EMPTY_ERROR = new OpResult(new Error(1,"登录信息不完整，请确保phone,mail,idNum,password字段不为空"));
    public static final OpResult TOKEN_UNKNOW_ERROR = new OpResult(new Error(1,"TOKEN无法识别,请重新登录"));
    public static final OpResult UPD_DUPLI_ERROR = new OpResult(new Error(1,"该手机号/邮箱/身份证号已有其他账户"));




    private OpResult(Error error){
        super(error);
    }

    public OpResult(Error error, int id){
        super(error,id);
    }

    public Object getData(){
        return super.getData();
    }

    public void setData(int id){
        super.setData(id);
    }

}
