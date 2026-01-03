package com.ruoyi.domain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("老人信息")
public class OldVo {

    @ApiModelProperty
    private Long oldId;

    @ApiModelProperty
    private String nickName;

    @ApiModelProperty
    private String relationship;

}
