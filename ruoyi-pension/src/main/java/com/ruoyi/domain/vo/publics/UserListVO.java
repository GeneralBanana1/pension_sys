package com.ruoyi.domain.vo.publics;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户列表")
public class UserListVO {

    private Long userId;

    private String nickName;

}
