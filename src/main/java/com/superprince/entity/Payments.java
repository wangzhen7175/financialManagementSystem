package com.superprince.entity;

import java.io.Serializable;
import java.util.Date;
public class Payments implements Serializable 
{
  private static final long serialVersionUID = 1256273897024133599L;
  
  private Integer paymentId;
  private String paymentValue;
  private String paymentName;
  private String paymentType;
  private String unit;
  private String unitName;
  private String description;
  private String day;
  private Date createTime;
  private String type;
  //private String typeName;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(Integer paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentValue() {
    return paymentValue;
  }

  public void setPaymentValue(String paymentValue) {
    this.paymentValue = paymentValue;
  }

  public String getPaymentName() {
    return paymentName;
  }

  public void setPaymentName(String paymentName) {
    this.paymentName = paymentName;
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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Payments{" +
            "paymentId=" + paymentId +
            ", paymentValue='" + paymentValue + '\'' +
            ", paymentName='" + paymentName + '\'' +
            ", paymentType='" + paymentType + '\'' +
            ", unit='" + unit + '\'' +
            ", unitName='" + unitName + '\'' +
            ", description='" + description + '\'' +
            ", day='" + day + '\'' +
            ", createTime=" + createTime +
            ", type='" + type + '\'' +
            '}';
  }
}
