package com.ruoyi.domain.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PeContactsQueryVO {
    @ApiModelProperty("联系人id")
    private Long contactsId;
    @ApiModelProperty("联系人名称")
    private String name;
    @ApiModelProperty("联系人手机号")
    private String phone;
    @ApiModelProperty("联系人备注")
    private String remark;
    @ApiModelProperty("是否为紧急联系人")
    private String isDefault;
}
