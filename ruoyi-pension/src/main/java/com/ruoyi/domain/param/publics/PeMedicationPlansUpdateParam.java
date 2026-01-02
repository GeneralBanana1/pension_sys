package com.ruoyi.domain.param.publics;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用药计划更新参数")
public class PeMedicationPlansUpdateParam {

    @ApiModelProperty("用药计划id")
    @TableId(type = IdType.AUTO)
    private long medicationPlansId;

    @ApiModelProperty("用户id")
    private long userId;

    @ApiModelProperty("药品名称")
    private String drugName;

    @ApiModelProperty("药品剂量")
    private String dosage;

    @ApiModelProperty("用药频率")
    private String frequency;

    @ApiModelProperty("是否激活")
    private String isActive;

    @ApiModelProperty("备注")
    private String remark;

}
