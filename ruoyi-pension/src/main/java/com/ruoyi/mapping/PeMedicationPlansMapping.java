package com.ruoyi.mapping;

import com.ruoyi.domain.entity.PeMedicationPlans;
import com.ruoyi.domain.param.publics.PeMedicationPlansCreateParam;
import com.ruoyi.domain.param.publics.PeMedicationPlansUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeMedicationPlansMapping {

    PeMedicationPlansMapping INSTANCE = Mappers.getMapper(PeMedicationPlansMapping.class);

    PeMedicationPlans to(PeMedicationPlansCreateParam param);

    PeMedicationPlans to(PeMedicationPlansUpdateParam param);
}
