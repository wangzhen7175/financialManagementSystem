package com.superprince.service;

import com.superprince.entity.Buget;
import com.superprince.model.Page;

import java.util.Map;



public interface BugetService
{
  public Buget getBuget(String month);

  public Page query(Map<String, Object> filter);

  public void addBuget(Buget model);

  public void deleteBuget(String id);
}
