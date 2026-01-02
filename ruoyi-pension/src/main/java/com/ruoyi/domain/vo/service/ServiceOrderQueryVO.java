package com.ruoyi.domain.vo.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("用户查询订单列表返回对象")
@Data
public class ServiceOrderQueryVO {

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("订单编号")
    private String orderNum;

    @ApiModelProperty("订单类型")
    private String type;

    @ApiModelProperty("订单详情")
    private String details;

    @ApiModelProperty("订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @ApiModelProperty("订单状态")
    private String state;

    @ApiModelProperty("发单人")
    private String customer;

    @ApiModelProperty("发单人手机号")
    private String phonenumber;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
