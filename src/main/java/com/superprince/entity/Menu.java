package com.superprince.entity;

import java.io.Serializable;

public class Menu implements Serializable 
{
  private static final long serialVersionUID = -2589245519866726792L;
  private Integer menuId;
  private String menuName;
  private String url;
  private Integer parentId;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getMenuId() {
    return menuId;
  }

  public void setMenuId(Integer menuId) {
    this.menuId = menuId;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  @Override
  public String toString() {
    return "Menu{" +
            "menuId=" + menuId +
            ", menuName='" + menuName + '\'' +
            ", url='" + url + '\'' +
            ", parentId=" + parentId +
            '}';
  }
}
