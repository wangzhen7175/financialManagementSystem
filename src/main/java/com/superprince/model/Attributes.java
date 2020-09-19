package com.superprince.model;

import java.io.Serializable;

public class Attributes implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String url;
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
}
