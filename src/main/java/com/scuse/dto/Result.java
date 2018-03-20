package com.scuse.dto;

/**
 * Created by Jaho on 2018/3/20.
 *
 * JSON返回结果泛型
 */

public class Result<Obj> {

    private Error error;
    private Obj data;


    public Result(){
        super();
    }

    public Result(Error error) {
        this.error = error;
        this.data = null;
    }

    public Result(Error error, Obj data) {
        this.error = error;
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Obj getData() {
        return data;
    }

    public void setData(Obj data) {
        this.data = data;
    }
}
