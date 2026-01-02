package com.ruoyi.domain.vo.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class UserInfoVO {

    private Long userId;

    private String nickName;

    private String avatar;

    private String phonenumber;

    private String email;

    private String sex;

    private String remark;

}
