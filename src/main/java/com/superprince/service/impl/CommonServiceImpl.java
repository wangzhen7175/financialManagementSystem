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
  private CommonMapper commonMapper;
  
  @Override
  public List<DataDict> getDatadictList()
  {
    return this.commonMapper.getDatadictList();
  }
  @Override
  public List<DataDict> getDatadictCataList(String catalog)
  {
    return this.commonMapper.getDatadictCataList(catalog);
  }
  @Override
  public void addDatadictData(DataDict model)
  {
    this.commonMapper.addDatadictData(model);
  }
  @Override
  public void delDatadictData(String id)
  {
    this.commonMapper.delDatadictData(id);
    
  }
  @Override
  public DataDict getDatadictDataById(String id)
  {
    return this.commonMapper.getDatadictDataById(id);
  }
  @Override
  public void updateDatadictData(DataDict model)
  {
    this.commonMapper.updateDatadictData(model);
    
  }

}
