package com.superprince.entity;

import java.io.Serializable;
import java.util.Date;
public class Payments implements Serializable 
{
  private static final long serialVersionUID = 1256273897024133599L;
  
  private Integer id;
  private String value;
  private String name;
  private String paymentType;
  private String unit;
  private String unitName;
  private String description;
  private String day;
  private Date crtTime;
  private String type;
  private String typeName;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public Date getCrtTime() {
    return crtTime;
  }

  public void setCrtTime(Date crtTime) {
    this.crtTime = crtTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public String toString() {
    return "Payments{" +
            "id=" + id +
            ", value='" + value + '\'' +
            ", name='" + name + '\'' +
            ", paymentType='" + paymentType + '\'' +
            ", unit='" + unit + '\'' +
            ", unitName='" + unitName + '\'' +
            ", description='" + description + '\'' +
            ", day='" + day + '\'' +
            ", crtTime=" + crtTime +
            ", type='" + type + '\'' +
            ", typeName='" + typeName + '\'' +
            '}';
  }
}
