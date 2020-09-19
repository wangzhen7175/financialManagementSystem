package com.superprince.mapper;

import java.util.List;
import java.util.Map;

import com.superprince.entity.Buget;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface BugetMapper extends BaseMapper<Buget>
{
	  public Buget getBugetByMonth(String month);

	  public List<Buget> queryPage(Map<String, Object> filter, RowBounds rb);

	  public int getTotal(Map<String, Object> map);

	  public void addBuget(Buget model);

	  public void deleteBuget(String id); 
}
