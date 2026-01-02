package com.ruoyi.domain.vo.publics;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("用药计划返回对象")
public class MedicationPlansListVO {

    @ApiModelProperty("用药计划ID")
    @TableId(type = IdType.AUTO)
    private long medicationPlansId;

    @ApiModelProperty("用户ID")
    private String nickName;

    @ApiModelProperty("药品名称")
    private String drugName;

    @ApiModelProperty("用药剂量")
    private String dosage;

    @ApiModelProperty("用药频率")
    private String frequency;

    @ApiModelProperty("是否启用")
    private String isActive;

    @ApiModelProperty("用药记录")
    private List<PeMedicationRecordListVO> records;

    @ApiModelProperty("备注")
    private String remark;

}
