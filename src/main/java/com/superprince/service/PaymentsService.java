package com.superprince.service;

import com.superprince.entity.Payments;
import com.superprince.model.Page;

import java.util.List;
import java.util.Map;




public interface PaymentsService
{
  Integer getWeekSum(Map<String, String> map);

  Integer getMonthSum(Map<String, String> map);

  Map<String,String> getMonthSumByType(String month, int days);

  List<Map<String,String>> getPayincomeData(String stryear, String strmonth, String monday, String sunday);

  Page query(Map<String, Object> filter);

  void addPayments(Payments model);

  void delPayments(String id);

  Payments getPaymentsById(String id);

  void updatePayments(Payments model);
  
}
