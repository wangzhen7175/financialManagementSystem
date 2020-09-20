package com.superprince.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.superprince.service.PaymentsService;
import com.superprince.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomePageController {

    @Resource
    private PaymentsService paymentsService;


    @RequestMapping("/payincome.do")
    @ResponseBody
    public Object getPayincomeData() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        String monday = DateUtils.getCurrentMonday();
        String sunday = DateUtils.getPreviousSunday();
        String stryear = String.valueOf(year);
        String smonth = String.valueOf(month);
        if (month < 10)
            smonth = String.format("0%s", month);
        String strmonth = String.valueOf(year) + smonth;
        List<Map<String, String>> result = paymentsService.getPayincomeData(stryear, strmonth, monday, sunday);
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
        Map<String, String> ret = this.paymentsService.getMonthSumByType(strmonth, days);
        return ret;
    }


}
