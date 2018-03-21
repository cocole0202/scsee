package com.scuse.controller;

import com.scuse.dto.Result;
import com.scuse.entity.Room;
import com.scuse.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Room")
public class RoomController {
    @Autowired
    RoomService roomService;

    /*
     * 添加Room
     * @param rooms: 要添加的考次
     * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addRoom(@RequestBody List<Room> rooms) {
        return roomService.addRoom(rooms);
    }

    /*
     * 删除Room
     * @param ids: 需要删除的room的id列表
     * @return 返回删除操作错误信息
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result delRoom(@RequestBody int[] ids) {
        return roomService.delRoom(ids);
    }

    /*
     * 更新Room
     * @param rooms: room列表
     * @return 返回更新操作错误信息
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Result updRoom(@RequestBody List<Room> rooms) {
        return roomService.updRoom(rooms);
    }


    /*
     * 获取Room
     * @return 返回操作错误信息以及Room列表
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result updRoom() {
        return roomService.getRooms();
    }
}
