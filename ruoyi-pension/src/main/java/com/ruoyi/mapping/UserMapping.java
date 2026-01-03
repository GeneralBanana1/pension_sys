package com.ruoyi.mapping;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.domain.param.user.UpdateUserInfoParam;
import com.ruoyi.domain.vo.user.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapping {

    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    UserInfoVO to(SysUser sysUser);

    SysUser to(UpdateUserInfoParam param);

}
