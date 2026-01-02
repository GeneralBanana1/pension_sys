package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.entity.PeMedicationPlans;
import com.ruoyi.domain.vo.publics.MedicationPlansListVO;
import com.ruoyi.mapper.PeMedicationPlansMapper;
import com.ruoyi.service.IPeMedicationPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeMedicationPlansServiceImpl extends ServiceImpl<PeMedicationPlansMapper, PeMedicationPlans>
        implements IPeMedicationPlansService {


    @Autowired
    private PeMedicationPlansMapper medicationPlansMapper;

    @Override
    public List<MedicationPlansListVO> list(Long userId) {
        return medicationPlansMapper.list(userId);
    }

}
