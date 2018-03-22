package com.scuse.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

@Service("userInfoTools")
public class UserInfoTools implements UserInfoToolsInterface{

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String Bit32(String SourceString) throws Exception {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(SourceString.getBytes());
        byte messageDigest[] = digest.digest();
        return toHexString(messageDigest);
    }

    public static String Bit16(String SourceString) throws Exception {
        return Bit32(SourceString).substring(8, 24);
    }

    @Override
    public int DateCompare(Date date1, Date date2) {
        if(date1.before(date2))
            return 1;
        else if(date1.equals(date2))
            return 0;
        else
            return -1;
    }

    @Override
    public String getATokenStr(){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        String token;

        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        token = sb.toString();

        return token;
    }

    @Override
    public String CreateMD5Code(String password) throws Exception {
        return Bit16(password);
    }


}
