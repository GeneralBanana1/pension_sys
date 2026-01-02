package com.ruoyi.mapping;

import com.ruoyi.domain.entity.PeHealthCondition;
import com.ruoyi.domain.param.user.PeHealthConditionParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeHealthConditionMapping {

    PeHealthConditionMapping INSTANCE = Mappers.getMapper(PeHealthConditionMapping.class);

    PeHealthCondition DTOToPO(PeHealthConditionParam peHealthConditionParam);

    PeHealthConditionParam POToDTO(PeHealthCondition peHealthCondition);

}
