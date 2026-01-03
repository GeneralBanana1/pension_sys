package com.ruoyi.domain.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class QueryOldParam {

    @ApiModelProperty("手机号码")
    private String phonenumber;

    @ApiModelProperty("用户昵称")
    private String nickName;

}
