package com.superprince.service;

import com.superprince.entity.Payments;
import com.superprince.model.Page;

import java.util.Map;




public interface PaymentsService
{

  Page query(Map<String, Object> filter);

  void addPayments(Payments model);

  void delPayments(String id);

  Payments getPaymentsById(String id);

  void updatePayments(Payments model);
  
}
