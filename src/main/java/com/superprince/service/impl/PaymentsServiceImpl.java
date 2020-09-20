package com.superprince.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.superprince.entity.Payments;
import com.superprince.mapper.PaymentsMapper;
import com.superprince.model.Page;
import com.superprince.service.PaymentsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;



@Service
public class PaymentsServiceImpl implements PaymentsService
{
  @Resource
  private PaymentsMapper paymentsMapper;

  @Override
  public Integer getWeekSum(Map<String,String> map)
  {
    return this.paymentsMapper.getWeekSum(map);

  }
  @Override
  public Integer getMonthSum(Map<String, String> map)
  {
    return this.paymentsMapper.getMonthSum(map);
  }
  @Override
  public Map<String, String> getMonthSumByType(String month,int days)
  {
    Map<String,String> retmap = new HashMap<String,String>();
    List<Map> list = this.paymentsMapper.getMonthSumByType(month + "%");
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
      Integer value = this.paymentsMapper.getDayValue(temp);
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
    Integer week1 = paymentsMapper.getWeekSum(map);
    map.put("paymenttype", "2");
    Integer week2 = paymentsMapper.getWeekSum(map);

    map = new HashMap<String,String>();
    map.put("paymenttype", "1");
    map.put("day", strmonth+"%");
    Integer month1 = paymentsMapper.getMonthSum(map);
    map.put("paymenttype", "2");
    Integer month2 = paymentsMapper.getMonthSum(map);

    map = new HashMap<String,String>();
    map.put("paymenttype", "1");
    map.put("day", stryear+"%");
    Integer year1 = paymentsMapper.getMonthSum(map);
    map.put("paymenttype", "2");
    Integer year2 = paymentsMapper.getMonthSum(map);

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
  @Override
  public Page query(Map<String, Object> filter)
  {
    int pageno = Integer.parseInt(filter.get("page").toString());
    int rows = Integer.parseInt(filter.get("rows").toString());
    int start = (pageno-1)*rows;
    
    filter.remove("page");
    filter.remove("rows");
    
    Map<String, Object> map = new HashMap<String, Object>();
    for (String o : filter.keySet())
    {
      if (filter.get(o) == null || filter.get(o).equals(""))
        continue;
      map.put(o, filter.get(o));
    }
    RowBounds rb = new RowBounds(start,rows);
    List<Payments> list = this.paymentsMapper.queryPage(map,rb);
    int total = this.paymentsMapper.getTotal(map);
    Page page = new Page();
    page.setRows(list);
    page.setTotal(total);
    return page;
  }

  @Override
  public void addPayments(Payments model)
  {
    model.setCreateTime(new Date());
    model.setDay(model.getDay().replaceAll("-", ""));
    this.paymentsMapper.addPayments(model);
    
  }

  @Override
  public void delPayments(String id)
  {
    this.paymentsMapper.delPayments(id);
    
  }

  @Override
  public Payments getPaymentsById(String id)
  {
    return this.paymentsMapper.getPaymentsById(id);
  }

  @Override
  public void updatePayments(Payments model)
  {
    model.setCreateTime(new Date());
    this.paymentsMapper.updatePayments(model);
    
  }
  
}
