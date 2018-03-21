package com.scuse;

import com.scuse.dto.Result;
import com.scuse.entity.Room;
import com.scuse.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTests {
    @Autowired
    RoomService roomService;
    /*
    * 测试GetAll方法
    */
    @Test
    public void testGet(){
        Result result = roomService.getRooms();
        System.out.println(result.getError().getMessage());
    }
    /*
    * 添加room测试
    * */
    @Test
    public void testAdd(){
        List<Room> rooms = new ArrayList<Room>();
        Room room = new Room();
        room.setId(500);
        room.setSiteId(300);
        room.setAddr("asdff");
        room.setCandLimit(123);
        rooms.add(room);
        Result result = roomService.addRoom(rooms);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 通过id删除room测试
    * */
    @Test
    public void testDel(){
        int[] ids= new int[]{400,500};
        Result result = roomService.delRoom(ids);
        System.out.println(result.getError().getMessage());
    }
    /*
    * 更新room测试
    * */
    @Test
    public void testUpd(){
        List<Room> rooms = new ArrayList<Room>();
        Room room = new Room();
        room.setId(400);
        room.setSiteId(300);
        room.setAddr("asdff");
        room.setCandLimit(123);
        rooms.add(room);
        Result result = roomService.updRoom(rooms);
        System.out.println(result.getError().getMessage());
    }
}
