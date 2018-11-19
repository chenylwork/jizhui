package com.yanshang.jizhui.util;

import javax.servlet.http.HttpSession;

import com.yanshang.jizhui.bean.User;


public class SessionUtil {
    public static User getsession(HttpSession session) {

        User user = (User) session.getAttribute("currentUser");
        System.out.println(user);
        return (User) session.getAttribute("currentUser");


    }


}
