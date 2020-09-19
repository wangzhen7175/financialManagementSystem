package com.superprince.mapper;

import com.superprince.entity.DataDict;
import com.superprince.entity.Menu;

import java.util.List;
import java.util.Map;

public interface CommonMapper extends BaseMapper<Menu>
{
	  public List<DataDict> getDatadictList();
	  public List<Menu> getAllMenu();
	  public List<DataDict> getDatadictCataList(String catalog);
	  public void addDatadictData(DataDict model);
	  public void delDatadictData(String id);
	  public DataDict getDatadictDataById(String id);
	  public void updateDatadictData(DataDict model);
	  public Integer getWeekSum(Map<String, String> map);
	  public Integer getMonthSum(Map<String, String> map);
	  public List<Map> getMonthSumByType(String month);
	  public Integer getDayValue(String day);
}
