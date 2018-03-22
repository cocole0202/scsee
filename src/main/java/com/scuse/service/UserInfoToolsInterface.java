package com.scuse.service;

import java.util.Date;

public interface UserInfoToolsInterface {
    /*
    比较日期
    @return 1 if date1 before date2
    @return 0 if date1 equals date2
    @return -1 if date1 after date2
     */
    public int DateCompare(Date date1, Date date2);

    /*
    生成MD5加密
    @param password, user password
    @return md5 code, len = 16
     */
    public String CreateMD5Code(String password) throws Exception;

    /*
       生成token
     */
    public String getATokenStr();
}
