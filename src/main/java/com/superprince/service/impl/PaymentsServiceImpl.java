package com.superprince.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  private PaymentsMapper dao;

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
    List<Payments> list = this.dao.queryPage(map,rb);
    int total = this.dao.getTotal(map);
    Page page = new Page();
    page.setRows(list);
    page.setTotal(total);
    return page;
  }

  @Override
  public void addPayments(Payments model)
  {
    model.setCrtTime(new Date());
    model.setDay(model.getDay().replaceAll("-", ""));
    this.dao.addPayments(model);
    
  }

  @Override
  public void delPayments(String id)
  {
    this.dao.delPayments(id);
    
  }

  @Override
  public Payments getPaymentsById(String id)
  {
    return this.dao.getPaymentsById(id);
  }

  @Override
  public void updatePayments(Payments model)
  {
    model.setCrtTime(new Date());
    this.dao.updatePayments(model);
    
  }
  
}
