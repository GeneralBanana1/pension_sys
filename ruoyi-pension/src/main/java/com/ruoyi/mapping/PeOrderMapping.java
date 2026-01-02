package com.ruoyi.mapping;

import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.param.user.UserOrderCreateParam;
import com.ruoyi.domain.param.user.UserOrderUpdateParam;
import com.ruoyi.domain.vo.service.ServiceOrderQueryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PeOrderMapping {

    PeOrderMapping INSTANCE = Mappers.getMapper(PeOrderMapping.class);

    PeOrder to(UserOrderCreateParam param);

    PeOrder to(UserOrderUpdateParam param);



}
