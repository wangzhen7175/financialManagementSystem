package com.superprince.service;

import com.superprince.entity.DataDict;
import com.superprince.entity.Menu;

import java.util.List;
import java.util.Map;

public interface CommonService {

   List<DataDict> getDatadictList();

   List<DataDict> getDatadictCataList(String catalog);

   void addDatadictData(DataDict model);

   void delDatadictData(String id);

   DataDict getDatadictDataById(String id);

   void updateDatadictData(DataDict model);

  /* Integer getWeekSum(Map<String, String> map);

   Integer getMonthSum(Map<String, String> map);

   Map<String,String> getMonthSumByType(String month, int days);

   List<Map<String,String>> getPayincomeData(String stryear, String strmonth, String monday, String sunday);
*/
}
