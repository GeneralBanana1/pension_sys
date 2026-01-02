package com.ruoyi.mapping;

import com.ruoyi.domain.entity.PeOldFamily;
import com.ruoyi.domain.param.user.OldFamilyParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeOldFamilyMapping {

    PeOldFamilyMapping INSTANCE = Mappers.getMapper(PeOldFamilyMapping.class);

    PeOldFamily to(OldFamilyParam param);

}
