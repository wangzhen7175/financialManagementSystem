package com.superprince.entity;

import java.io.Serializable;

public class DataDict implements Serializable 
{
  private static final long serialVersionUID = -2692788275202369407L;
  
  private Integer id;
  private String catalog;
  private String code;
  private String codeName;
  private String userId;
  public String getUserId() {
	return userId;
}
  public void setUserId(String userId) {
	this.userId = userId;
}
  public Integer getId()
  {
    return id;
  }
  public void setId(Integer id)
  {
    this.id = id;
  }
  public String getCatalog()
  {
    return catalog;
  }
  public void setCatalog(String catalog)
  {
    this.catalog = catalog;
  }
  public String getCode()
  {
    return code;
  }
  public void setCode(String code)
  {
    this.code = code;
  }
  public String getCodeName()
  {
    return codeName;
  }
  public void setCodeName(String codeName)
  {
    this.codeName = codeName;
  }
  
  
}
