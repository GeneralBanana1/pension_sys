package com.ruoyi.domain.param.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ApiModel("用户创建健康信息参数")
public class PeHealthConditionParam {
    @ApiModelProperty("老人id")
    @NotNull(message = "老人id不能为空")
    private Long userId;
    @ApiModelProperty("血压")
    private String bloodPressure;
    @ApiModelProperty("心率")
    private String heartRate;
    @ApiModelProperty("血氧")
    private String bloodOxygen;
    @ApiModelProperty("体温")
    private String bodyTemperature;
    @ApiModelProperty("空腹血糖")
    private String fastingBloodSugar;
    @ApiModelProperty("餐后血糖")
    private String bloodSugar2hAfterMeal;
    @ApiModelProperty("运动量")
    private String mobilityLevel;
    @ApiModelProperty("备注")
    private String remark;
    @TableField(exist = false)
    private String create_time;

}
