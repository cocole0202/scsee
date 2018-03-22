package com.scuse.filter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scuse.entity.Admin;
import com.scuse.entity.Candidate;
import com.scuse.entity.Review;
import com.scuse.mapper.AdminMapper;
import com.scuse.mapper.CandidateMapper;
import com.scuse.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
拦截器
 */

public class URLInterceptor implements HandlerInterceptor {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    private void printErrorInfo(HttpServletResponse response, String info) throws IOException {
        String data = info;
        OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
        response.setHeader("content-type", "text/html;charset=UTF-8");
        byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
        outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
    }

    /**
     拦截器从请求头部中获取token并查询用户身份，将结果保存在id,type中

     验证用户身份时，需要满足：
     1.用户在admin或candidate表中有数据
     2.用户已通过审核,即review表中无数据
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        System.out.println("------拦截器-------");
        //1.从header中获取token
        String token = request.getHeader("token");
        System.out.println("TOKEN:"+token);

        //2.根据token在数据库中查询用户信息及身份
        Candidate candidate = candidateMapper.selectByToken(token);
        if(candidate!=null) //该token对应身份为考生
        {
            //3.验证是否通过审核, type=1考生审核
            Review review = reviewMapper.selectType(new Review(candidate.getId(),1));
            if(review!=null) {
                if (review.getType() == 1) //未通过审核
                {
                    printErrorInfo(response, "您是考生。已注册，但未通过审核，请等待审核。");
                    return false;
                }
            }

            //检查token是否过期
            Date date = candidate.getExpiredDate();
            if(date.before(new Date())){
                //token已过期
                printErrorInfo(response, "token已过期，请重新登录。");
                return false;
            }

            //4.添加获取token后查询到的用户身份信息
            request.setAttribute("id",candidate.getId());
            request.setAttribute("type",1);
        }
        else{
            Admin admin = adminMapper.selectByToken(token);
            if(admin!=null){ //该token对应身份为管理员
                //3.验证是否通过审核，type=7管理员审核
                Review review = reviewMapper.selectType(new Review(admin.getId(),7));
                if(review!=null) {
                    if (review.getType() == 7) //未通过审核
                    {
                        printErrorInfo(response, "您是管理员。已注册，但未通过审核，请等待审核。");
                        return false;
                    }
                }

                //检查token是否过期
                Date date = admin.getExpiredDate();
                if(date.before(new Date())){
                    //token已过期
                    printErrorInfo(response, "token已过期，请重新登录。");
                    return false;
                }
                request.setAttribute("id",admin.getId());
                request.setAttribute("type","2");
            }
            else //该token在数据库中无数据,拒绝访问
            {
                printErrorInfo(response,"请先登录！");
                return false;
            }
        }

        return true;
    }
    }