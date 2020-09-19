package com.superprince.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.superprince.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomePageController {

    @Resource
    private CommonService commonService;



    @RequestMapping("/payincome.do")
    @ResponseBody
    public Object getPayincomeData() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        String monday = this.getCurrentMonday();
        String sunday = this.getPreviousSunday();
        String stryear = String.valueOf(year);
        String smonth = String.valueOf(month);
        if (month < 10)
            smonth = String.format("0%s", month);
        String strmonth = String.valueOf(year) + smonth;
        List<Map<String, String>> result = this.commonService.getPayincomeData(stryear, strmonth, monday, sunday);
        return result;
    }

    @RequestMapping("/getMonthSumByType.do")
    @ResponseBody
    public Object getMonthSumByType() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String smonth = String.valueOf(month);
        if (month < 10)
            smonth = String.format("0%s", month);
        String strmonth = String.valueOf(year) + smonth;

        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Map<String, String> ret = this.commonService.getMonthSumByType(strmonth, days);
        return ret;
    }

    // 获得当前日期与本周一相差的天数
    private int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得当前周- 周一的日期
    private String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得当前周- 周日 的日期
    private String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String preMonday = df.format(monday);
        return preMonday;
    }
}
