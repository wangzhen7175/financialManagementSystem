package com.superprince.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.superprince.entity.Buget;
import com.superprince.mapper.BugetMapper;
import com.superprince.model.Page;
import com.superprince.service.BugetService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;



@Service
public class BugetServiceImpl implements BugetService
{
  @Resource
  BugetMapper bugetMapper;

  @Override
  public Buget getBuget(String month)
  {
    if (month == null || month.equals(""))
      return null;
    return this.bugetMapper.getBugetByMonth(month);
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
    List<Buget> list = this.bugetMapper.queryPage(map,rb);
    int total = this.bugetMapper.getTotal(map);
    Page page = new Page();
    page.setRows(list);
    page.setTotal(total);
    return page;
  }

  @Override
  public void addBuget(Buget model)
  {
    this.bugetMapper.addBuget(model);
    
  }

  @Override
  public void deleteBuget(String id)
  {
    this.bugetMapper.deleteBuget(id);
    
  }
}
