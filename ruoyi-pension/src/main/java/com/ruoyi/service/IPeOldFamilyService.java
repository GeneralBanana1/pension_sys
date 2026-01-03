package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.entity.PeOldFamily;
import com.ruoyi.domain.vo.service.CustomerVo;
import com.ruoyi.domain.vo.user.OldVo;

import java.util.List;

public interface IPeOldFamilyService extends IService<PeOldFamily> {

    List<OldVo> list(Long userId);
}
