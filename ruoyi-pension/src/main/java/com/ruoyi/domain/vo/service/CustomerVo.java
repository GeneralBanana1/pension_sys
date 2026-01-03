package com.ruoyi.domain.vo.service;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("客户信息")
public class CustomerVo {

    private Long customerId;

    private String customer;
}
