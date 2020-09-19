package com.superprince.model;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable
{
  private static final long serialVersionUID = 1L;
  private List              rows;
  private int               total;
  
  public List getRows()
  {
    return this.rows;
  }
  
  public void setRows(List rows)
  {
    this.rows = rows;
  }
  
  public int getTotal()
  {
    return this.total;
  }
  
  public void setTotal(int total)
  {
    this.total = total;
  }
}
