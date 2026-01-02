package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.entity.PeMedicationRecord;
import com.ruoyi.mapper.PeMedicationRecordMapper;
import com.ruoyi.service.IPeMedicationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeMedicationRecordServiceImpl extends ServiceImpl<PeMedicationRecordMapper, PeMedicationRecord>
        implements IPeMedicationRecordService {

    @Autowired
    private PeMedicationRecordMapper medicationRecordMapper;

}
