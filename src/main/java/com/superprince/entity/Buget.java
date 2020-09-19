package com.superprince.entity;

import java.io.Serializable;

public class Buget implements Serializable 
{
  private static final long serialVersionUID = 3296112127650560828L;
  private Integer id;
  private String month;
  private String value;
  private String unit;
  private String unitName;
  
  public String getUnit()
  {
    return unit;
  }
  public void setUnit(String unit)
  {
    this.unit = unit;
  }
  public String getUnitName()
  {
    return unitName;
  }
  public void setUnitName(String unit)
  {
    this.unitName = unit;
  }
  public Integer getId()
  {
    return id;
  }
  public void setId(Integer id)
  {
    this.id = id;
  }
  public String getMonth()
  {
    return month;
  }
  public void setMonth(String month)
  {
    this.month = month;
  }
  public String getValue()
  {
    return value;
  }
  public void setValue(String value)
  {
    this.value = value;
  }
  
}
