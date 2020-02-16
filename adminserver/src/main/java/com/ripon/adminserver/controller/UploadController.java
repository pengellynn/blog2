package com.ripon.adminserver.controller;

import com.ripon.adminserver.dto.Response;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UploadController {
    @PostMapping("/imgUpload")
    public Response uploadImage(@RequestParam("image") MultipartFile image, HttpServletRequest request) throws Exception{
        //获取根目录
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        File rootFile=new File(path);
        if(!rootFile.exists()) {
            rootFile = new File("");
        }
        System.out.println("path:"+rootFile.getAbsolutePath());

        File savePath = new File(rootFile.getAbsolutePath(),"static/images/");
        if(!savePath.exists()&&!savePath.isDirectory()) {
            savePath.mkdirs();
        }
        System.out.println("savePath url:"+savePath.getAbsolutePath());
        String originalFileName = image.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.indexOf(".")+1);
        String name = originalFileName.substring(0, originalFileName.indexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        String imgName = date+name+"."+type;
        File targetFile = new File(savePath, imgName);
        try {
            image.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return Response.success("/images/" + imgName);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.fail("上传失败");
        }

    }
}
