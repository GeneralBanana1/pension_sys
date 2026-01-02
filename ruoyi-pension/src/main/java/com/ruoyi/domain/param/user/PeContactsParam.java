package com.ruoyi.domain.param.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api("用户创建联系人参数")
@Data
@NoArgsConstructor
public class PeContactsParam {

    @NotNull(message = "联系人id不能为空")
    @ApiModelProperty("联系人id")
    private Long contactsId;
    @ApiModelProperty("联系人所属老人id")
    @NotNull(message = "联系人所属老人id不能为空")
    private Long userId;
    @NotBlank(message = "联系人名称不能为空")
    @ApiModelProperty("联系人名称")
    private String name;
    @NotBlank(message = "联系人手机号不能为空")
    @ApiModelProperty("联系人手机号")
    private String phone;
    @ApiModelProperty("联系人备注")
    private String remark;
    @NotBlank(message = "是否为紧急联系人不能为空")
    @ApiModelProperty("是否为紧急联系人")
    private String isDefault;
}
