package com.ruoyi.mapping;

import com.ruoyi.domain.entity.PeMedicationRecord;
import com.ruoyi.domain.param.publics.PeMedicationRecordsCreateParam;
import com.ruoyi.domain.param.publics.PeMedicationRecordsUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeMedicationRecordMapping {

    PeMedicationRecordMapping INSTANCE = Mappers.getMapper(PeMedicationRecordMapping.class);

    PeMedicationRecord to(PeMedicationRecordsCreateParam param);

    PeMedicationRecord to(PeMedicationRecordsUpdateParam param);

}
