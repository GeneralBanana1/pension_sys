package com.ruoyi.domain.param.publics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("用药计划创建参数")
public class PeMedicationPlansCreateParam {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private long userId;

    @ApiModelProperty("药品名称")
    @NotBlank(message = "药品名称不能为空")
    private String drugName;

    @ApiModelProperty("药品剂量")
    @NotBlank(message = "药品剂量不能为空")
    private String dosage;

    @ApiModelProperty("用药频率")
    @NotBlank(message = "用药频率不能为空")
    private String frequency;

    @ApiModelProperty("是否激活")
    @NotBlank(message = "是否激活不能为空")
    private String isActive;

    @ApiModelProperty("备注")
    private String remark;

}
