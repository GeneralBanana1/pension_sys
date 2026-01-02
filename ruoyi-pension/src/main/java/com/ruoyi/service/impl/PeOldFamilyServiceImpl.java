package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.entity.PeOldFamily;
import com.ruoyi.mapper.PeOldFamilyMapper;
import com.ruoyi.service.IPeOldFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeOldFamilyServiceImpl extends ServiceImpl<PeOldFamilyMapper, PeOldFamily> implements IPeOldFamilyService {

    @Autowired
    private PeOldFamilyMapper oldFamilyMapper;
}
