package com.superprince.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.superprince.entity.DataDict;
import com.superprince.service.CommonService;
import com.superprince.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class CommonController
{
  @Resource
  private CommonService service;
  @Resource
  private UserService userService;
  
  @RequestMapping("/listDatadict")
  @ResponseBody
  public Object getDatadictList(Model model)
  {
    return this.service.getDatadictList();
  }
  @RequestMapping("/listDatadictCata")
  @ResponseBody
  public Object getDatadictCataList(@RequestParam String catalog)
  {
    return this.service.getDatadictCataList(catalog);
  }
  @RequestMapping("/addDatadictData")
  @ResponseBody
  public Object addDatadictData(DataDict model)
  {
    this.service.addDatadictData(model);
    Map<String,String> map = new HashMap<String,String>();
    map.put("success", "true");
    map.put("msg", "添加成功！");
    return map;
  }
  @RequestMapping("/deleteDatadictData")
  @ResponseBody
  public Object delDatadictData(@RequestParam String id)
  {
    this.service.delDatadictData(id);
    Map<String,String> map = new HashMap<String,String>();
    map.put("success", "true");
    map.put("msg", "删除成功！");
    return map;
  }
  @RequestMapping("/preUpdateDatadictData")
  public Object preUpdateDatadictData(@RequestParam String id,Model model)
  {
    DataDict data = this.service.getDatadictDataById(id);
    model.addAttribute("entity", data);
    return "/preupdatedatadictdata";
  }
  @RequestMapping("/updateDatadictData")
  @ResponseBody
  public Object updateDatadictData(DataDict model)
  {
    this.service.updateDatadictData(model);
    Map<String,String> map = new HashMap<String,String>();
    map.put("success", "true");
    map.put("msg", "修改成功！");
    return map;
  }
  
}
