package com.ruoyi.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PeOrder {

  @TableId(type = IdType.AUTO)
  private Long orderId;

  private String orderNum;

   private String type;

  private String details;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date time;

  private String state;

  private Long customerId;

  @TableField(insertStrategy = FieldStrategy.IGNORED)
  private Long serviceId;

  private String remark;

  private String delFlag;

  @TableField(fill = FieldFill.INSERT)
  private String createBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.UPDATE)
  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(fill = FieldFill.UPDATE)
  private Date updateTime;
}
