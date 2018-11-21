package com.yanshang.jizhui.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yanshang.jizhui.bean.User;
import com.yanshang.jizhui.service.UserService;


@RestController
public class UploadImgController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("img") MultipartFile img, String nickname, String phone, String username,
                     HttpServletRequest request) {
        String contentType = img.getContentType();
        User user = userService.finduserbyusername(username);
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setRolename("专家");
        String fileName = System.currentTimeMillis() + img.getOriginalFilename();
        System.out.println(fileName);
        String destFileName = "D:/imgs/" + File.separator + fileName;
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();
        user.setImage("http://211.149.194.181:80/image/" + fileName);
        userService.save(user);
        try {
            img.transferTo(destFile);
            System.out.println(destFile);
        } catch (IllegalStateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //返回json
        return "上传成功!!!";
    }


}