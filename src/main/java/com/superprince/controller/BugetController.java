package com.superprince.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.superprince.entity.Buget;
import com.superprince.service.BugetService;
import com.superprince.service.CommonService;
import com.superprince.service.PaymentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BugetController
{
  @Resource
  BugetService service;
  @Resource
  CommonService commonService;
  @Resource
  PaymentsService paymentsService;
  
  @RequestMapping("/query")
  @ResponseBody
  public Object query(@RequestParam Map<String,Object> filter)
  {
    return this.service.query(filter);
  }
  @RequestMapping("/getBuget")
  @ResponseBody
  public Object getBuget(@RequestParam String month)
  {
    if (month == null || month.equals(""))
      return "选择日期后才可查看相应月份的预算！";
    Buget buget = this.service.getBuget(month);
    if (buget == null)
      return "本月没有预算计划！";
    Map<String,String> map = new HashMap<String,String>();
    map.put("paymenttype", "2");
    map.put("day", month+"%");
    Integer pay = this.paymentsService.getMonthSum(map);
    return String.format("%s月份预算为：%s,已支出：%s！", buget.getMonth(),buget.getValue(),pay == null?"0":pay.toString());
  }
  @RequestMapping("/addBuget")
  @ResponseBody
  public Object addBuget(Buget model)
  {
    this.service.addBuget(model);
    Map<String,String> map = new HashMap<String,String>();
    map.put("success", "true");
    map.put("msg", "添加成功！");
    return map;
  }
  @RequestMapping("/deleteBuget")
  @ResponseBody
  public Object deleteBuget(@RequestParam String id)
  {
    this.service.deleteBuget(id);
    Map<String,String> map = new HashMap<String,String>();
    map.put("success", "true");
    map.put("msg", "删除成功！");
    return map;
  }
}
