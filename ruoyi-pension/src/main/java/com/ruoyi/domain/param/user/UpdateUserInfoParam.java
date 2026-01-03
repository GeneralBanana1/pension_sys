package com.ruoyi.domain.param.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("更新用户信息参数")
public class UpdateUserInfoParam {

    private Long userId;

    private String nickName;

    private String avatar;

    private String phonenumber;

    private String email;

    private String sex;

    private String remark;

}
