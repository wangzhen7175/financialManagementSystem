package com.superprince.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.superprince.entity.DataDict;
import com.superprince.entity.Menu;
import com.superprince.mapper.CommonMapper;
import com.superprince.service.CommonService;
import org.springframework.stereotype.Service;



@Service
public class CommonServiceImpl implements CommonService
{

  @Resource
  private CommonMapper dao;
  
  @Override
  public List<DataDict> getDatadictList()
  {
    return this.dao.getDatadictList();
  }
  @Override
  public List<Menu> getAllMenu()
  {
    return this.dao.getAllMenu();
  }
  @Override
  public List<DataDict> getDatadictCataList(String catalog)
  {
    return this.dao.getDatadictCataList(catalog);
  }
  @Override
  public void addDatadictData(DataDict model)
  {
    this.dao.addDatadictData(model);
  }
  @Override
  public void delDatadictData(String id)
  {
    this.dao.delDatadictData(id);
    
  }
  @Override
  public DataDict getDatadictDataById(String id)
  {
    return this.dao.getDatadictDataById(id);
  }
  @Override
  public void updateDatadictData(DataDict model)
  {
    this.dao.updateDatadictData(model);
    
  }
  @Override
  public Integer getWeekSum(Map<String,String> map)
  {
    return this.dao.getWeekSum(map);
    
  }
  @Override
  public Integer getMonthSum(Map<String, String> map)
  {
    return this.dao.getMonthSum(map);
  }
  @Override
  public Map<String, String> getMonthSumByType(String month,int days)
  {
    Map<String,String> retmap = new HashMap<String,String>();
    List<Map> list = this.dao.getMonthSumByType(month + "%");
    String data = "[{name:'花费',data:[";
    for (Map m:list)
    {
      String temp = "['%s',";
      data += String.format(temp, m.get("typename"));
      temp = "%s],";
      data += String.format(temp, m.get("value"));
    }
    if (data.length() > 18)
      data = data.substring(0, data.length() - 1);
    data += "]}]";
    retmap.put("data1", data);
    
    data = "[{name:'日期',data:[";
    String beginday = month + "01";
    int ibeginday = Integer.parseInt(beginday);
    for (int i = 0; i < days; i++)
    {
      String temp = String.valueOf(ibeginday + i);
      Integer value = this.dao.getDayValue(temp);
      data += String.format("['%d号',", Integer.parseInt(temp.substring(6)));
      data += String.format("%s],", value == null?"0":value.toString());
    }
    if (data.length() > 18)
      data = data.substring(0, data.length() - 1);
    data += "]}]";
    retmap.put("data2", data);
    return retmap;
  }
  @Override
  public List<Map<String, String>> getPayincomeData(String stryear, String strmonth, String monday, String sunday)
  {
    List<Map<String,String>> result = new ArrayList<Map<String,String>>();
    Map<String,String> map = new HashMap<String,String>();
    map.put("paymenttype", "1");
    map.put("monday", monday);
    map.put("sunday", sunday);
    Integer week1 = dao.getWeekSum(map);
    map.put("paymenttype", "2");
    Integer week2 = dao.getWeekSum(map);
    
    map = new HashMap<String,String>();
    map.put("paymenttype", "1");
    map.put("day", strmonth+"%");
    Integer month1 = dao.getMonthSum(map);
    map.put("paymenttype", "2");
    Integer month2 = dao.getMonthSum(map);
    
    map = new HashMap<String,String>();
    map.put("paymenttype", "1");
    map.put("day", stryear+"%");
    Integer year1 = dao.getMonthSum(map);
    map.put("paymenttype", "2");
    Integer year2 = dao.getMonthSum(map);
    
    Map<String,String> data1 = new HashMap<String,String>();
    data1.put("type", "收入");
    data1.put("week", week1 == null?"0":week1.toString());
    data1.put("month", month1 == null?"0":month1.toString());
    data1.put("year", year1 == null?"0":year1.toString());
    result.add(data1);
    
    data1 = new HashMap<String,String>();
    data1.put("type", "支出");
    data1.put("week", week2 == null?"0":week2.toString());
    data1.put("month", month2 == null?"0":month2.toString());
    data1.put("year", year2 == null?"0":year2.toString());
    result.add(data1);
    return result;
  }
}
