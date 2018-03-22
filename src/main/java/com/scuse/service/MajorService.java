package com.scuse.service;


import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Major;
import com.scuse.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Sheng Junyi on 2018/3/20
 *
 * Major 相关表操作
 */

@Repository
public class MajorService {

    @Autowired
    MajorMapper majorMapper;

    /*
    * 添加Major
    * @param majores: major列表
    * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result addMajor(List<Major> majors){
        try{
            for(Major major: majors){
                //对于每个major，查询在数据库中是否有此项数据
                Major findMajor = majorMapper.selectByPrimaryKey(major.getId());
                if(findMajor!=null){
                    //执行更新操作
                    majorMapper.updateByPrimaryKeySelective(major);
                }
                else{
                    //执行插入操作
                    majorMapper.insertSelective(major);
                }
            }
        }catch(Exception e){
            return OpResult.ADD_ERROR;
        }
        return  OpResult.ADD_UPD_SUCCESS;
    }

    /*
    * 删除Major
    * @param ids : 需要删除的majores的id列表
    * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delMajor(List<Integer> ids){
        try{
            for(Integer id: ids){
                majorMapper.deleteByPrimaryKey(id);
            }
        }catch(Exception e){
            return OpResult.DEL_ERROR;
        }
        return  OpResult.DEL_SUCCESS;
    }

     /*
    * 更新Major
    * @param majores : major列表
    * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updMajor(List<Major> majors){
        try{
            //先进行添加和更新操作，直接调用addMajor函数
            addMajor(majors);
            //获取数据库中所有专业
            List<Major> allMajor_DB = majorMapper.getAll();
            //比较数据库和传入列表，删除传入列表中没有的专业
            for(Major major_DB: allMajor_DB){
                boolean exist = false;
                //在传入列表中查找
                for(Major major_in: majors){
                    if(major_DB.getId()==major_in.getId()){
                        //该条目存在,不需要删除
                        exist = true;
                        break;
                   }
                }
                if(exist==false){
                    //该条目不存在，从数据库中删除
                    majorMapper.deleteByPrimaryKey(major_DB.getId());
                }
            }
        }catch (Exception e){
            return  OpResult.UPD_ERROR;
        }
        return OpResult.UPD_SUCCESS;
    }

     /*
    * 获取Major
    * @return 返回操作错误信息以及Major列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getMajors(){
        try{
            List<Major> majors= majorMapper.getAll();
            Result<List<Major>> result= new Result<>();
            result.setError(new Error(0,"获取专业信息成功"));
            result.setData(majors);
            return result;
        }catch (Exception e){
            return  OpResult.GET_ERROR;
        }
    }
}


