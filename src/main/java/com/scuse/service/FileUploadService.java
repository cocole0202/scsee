package com.scuse.service;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.core.io.InputStreamSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service(value="fileUploadService")
public class FileUploadService {

    private List<String> fileType = new ArrayList<String>(); //允许上传的文件类型

    public void addAcceptFileType(String type){
        fileType.add(type);
    }

    public String upload(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest)request);
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        String name=params.getParameter("phone");
        System.out.println("phone:"+name);
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String uploadFilePath = file.getOriginalFilename();
                    String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.lastIndexOf('.') + 1, uploadFilePath.length()).toLowerCase();
                    System.out.println("uploadFileSuffix:" + uploadFileSuffix);
                    if(!fileType.contains(uploadFileSuffix)){
                        return "Wrong file type";
                    }
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File("upload/"+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "Wrong";
                }
            } else {
                return "File empty";
            }
        }
        return "upload successful";
    }

}