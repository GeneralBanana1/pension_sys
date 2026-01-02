package com.ruoyi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.domain.entity.PeHealthCondition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeHealthConditionMapper extends BaseMapper<PeHealthCondition> {
    int updateHealth(PeHealthCondition peHealthCondition);
}
