package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.entity.PeMedicationPlans;
import com.ruoyi.domain.vo.publics.MedicationPlansListVO;

import java.util.List;

public interface IPeMedicationPlansService extends IService<PeMedicationPlans> {

    List<MedicationPlansListVO> list(Long userId);

}
