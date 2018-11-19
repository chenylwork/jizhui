package com.yanshang.jizhui.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeTools {
    public static String getStringBySDate(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = formatter.format(date);

        return time;


    }

    public static List<String> getInitMonthMapWithZero() {
        List<String> list = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            int k = c.get(Calendar.YEAR);
            int j = c.get(Calendar.MONTH) + 1 - i;
            String date = "";
            if (j >= 1) {
                date = k + "-" + (j >= 10 ? "" : "0") + j;
            } else {
                int p = 11 - i;//剩余循环次数
                int m = c.get(Calendar.YEAR) - 1;
                int n = c.get(Calendar.MONTH) + 2 + p;
                date = m + "-" + (n >= 10 ? "" : "0") + n;
            }
            list.add(date);
        }
        return list;
    }

}
