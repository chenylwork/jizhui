package com.yanshang.jizhui.util;

public class ResultUtil {
    public static String getresult(String result) {

        String content = "";
        char[] chars = result.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
            if (Integer.parseInt(chars[i] + "") < 5) {
                content = content + "您的第" + (i + 1) + "号脊柱有伤害";
            }


        }


        return content;

    }

    public static void main(String[] args) {
        String m = getresult("9777777111");
        System.out.println(m);
    }
}
