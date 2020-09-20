package com.superprince.entity;

import java.io.Serializable;

public class Menu implements Serializable 
{
  private static final long serialVersionUID = -2589245519866726792L;
  private Integer menuId;
  private String menuName;
  private String menuUrl;
  private Integer parentMenuId;

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

  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
  }

  public Integer getParentMenuId() {
    return parentMenuId;
  }

  public void setParentMenuId(Integer parentMenuId) {
    this.parentMenuId = parentMenuId;
  }

  @Override
  public String toString() {
    return "Menu{" +
            "menuId=" + menuId +
            ", menuName='" + menuName + '\'' +
            ", menuUrl='" + menuUrl + '\'' +
            ", parentMenuId=" + parentMenuId +
            '}';
  }
}
