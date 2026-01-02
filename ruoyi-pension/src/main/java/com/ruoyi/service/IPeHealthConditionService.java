package com.ruoyi.service;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.param.user.PeHealthConditionParam;

public interface IPeHealthConditionService {
    R selectById(Long customerId);

    R addHealthCondition(PeHealthConditionParam peHealthCondition);

    R updateHealthCondition(PeHealthConditionParam peHealthConditionParam);

}
