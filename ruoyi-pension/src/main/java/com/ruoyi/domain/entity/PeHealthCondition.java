package com.ruoyi.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class PeHealthCondition {

  @TableId(type = IdType.AUTO)
  private long healthConditionId;

  private long userId;

  private String bloodPressure;

  private String heartRate;

  private String bloodOxygen;

  private String bodyTemperature;

  private String fastingBloodSugar;


  @TableField(value = "blood_sugar_2h_after_meal")
  private String bloodSugar2hAfterMeal;

  private String mobilityLevel;

  private String remark;

  private String delFlag;

  @TableField(fill = FieldFill.INSERT)
  private String createBy;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.UPDATE)
  private String updateBy;

  @TableField(fill = FieldFill.UPDATE)
  private Date updateTime;


}
