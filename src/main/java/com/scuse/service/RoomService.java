package com.scuse.service;

import com.scuse.dto.Error;
import com.scuse.dto.OpResult;
import com.scuse.dto.Result;
import com.scuse.entity.Room;
import com.scuse.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Room 相关跨表操作
 */
@Repository
public class RoomService {

    @Autowired
    RoomMapper roomMapper;

    /*
     * 添加room
     * @param room: room列表
     * @return 返回添加操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public
    Result addRoom(List<Room> rooms) {
        try{
            for (Room room:rooms) {
                roomMapper.insert(room);
            }
        }catch (Exception e) {
            return OpResult.ADD_ERROR;
        }

        return OpResult.ADD_SUCCESS;
    }

    /*
     * 删除Room
     * @param ids: 需要删除的room的id列表
     * @return 返回删除操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result delRoom(int[] ids) {
        try{
            for (Integer id:ids) {
                roomMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e) {
            return OpResult.DEL_ERROR;
        }

        return OpResult.DEL_SUCCESS;
    }


    /*
     * 更新Room
     * @param Room: room列表
     * @return 返回更新操作错误信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result updRoom(List<Room> rooms) {
        try{
            for (Room room:rooms) {
                roomMapper.updateByPrimaryKeySelective(room);
            }
        }catch (Exception e) {
            return OpResult.UPD_ERROR;
        }

        return OpResult.UPD_SUCCESS;
    }


    /*
     * 获取room
     * @return 返回操作错误信息以及Room列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=3600,rollbackFor=Exception.class)
    public Result getRooms() {
        try{
            List<Room> rooms = roomMapper.getAll();
            Result<List<Room>> result = new Result<>();
            result.setError(new Error(0, "获取考场信息成功"));
            result.setData(rooms);
            return result;
        }catch (Exception e) {
            return OpResult.GET_ERROR;
        }
    }
}
