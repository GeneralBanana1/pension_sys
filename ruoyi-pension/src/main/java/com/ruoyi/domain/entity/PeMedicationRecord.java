package com.ruoyi.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PeMedicationRecord {

  @TableId(type = IdType.AUTO)
  private long medicationRecordId;

  private long medicationPlansId;

  private String remark;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date time;

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
