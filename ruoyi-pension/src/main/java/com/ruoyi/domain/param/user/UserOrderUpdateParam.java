package com.ruoyi.domain.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("用户更新订单参数")
@Data
public class UserOrderUpdateParam {

    @ApiModelProperty("订单id")
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    @ApiModelProperty("订单类型")
    @NotBlank(message = "订单类型不能为空")
    private String type;

    @ApiModelProperty("订单详情")
    @NotBlank(message = "订单详情不能为空")
    private String details;

    @ApiModelProperty("订单时间")
    @NotNull(message = "订单时间不能为空")
    private Date time;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("老人id")
    @NotNull(message = "老人id不能为空")
    private Long customerId;

}
