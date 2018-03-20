package com.scuse.dto;

/**
 * Created by Jaho on 2018/3/20.
 *
 * 增删改查操作返回结果
 */
public class OpResult extends Result<Object> {

    public static final OpResult ADD_SUCCESS = new OpResult(new Error(0,"添加成功"));
    public static final OpResult DEL_SUCCESS = new OpResult(new Error(0,"删除成功"));
    public static final OpResult UPD_SUCCESS = new OpResult(new Error(0,"更新成功"));

    public static final OpResult ADD_ERROR = new OpResult(new Error(1,"添加失败"));
    public static final OpResult DEL_ERROR = new OpResult(new Error(1,"删除失败"));
    public static final OpResult UPD_ERROR = new OpResult(new Error(1,"更新失败"));
    public static final OpResult GET_ERROR = new OpResult(new Error(1,"信息查询失败"));


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
