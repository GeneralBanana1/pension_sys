package com.ruoyi.domain.vo.service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("服务人员修改订单状态参数")
@Data
public class ServiceUpdateStateParam {

    @ApiModelProperty("订单id")
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    @ApiModelProperty("订单状态")
    @NotBlank(message = "订单状态不能为空")
    private String state;

}
