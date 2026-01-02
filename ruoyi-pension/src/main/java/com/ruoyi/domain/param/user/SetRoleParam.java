package com.ruoyi.domain.param.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("设置角色参数")
@Data
public class SetRoleParam {


    private Long userId;

    private Long roleId;

}
