package com.ruoyi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.domain.entity.PeMedicationPlans;
import com.ruoyi.domain.vo.publics.MedicationPlansListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PeMedicationPlansMapper extends BaseMapper<PeMedicationPlans> {

    List<MedicationPlansListVO> list(@Param("userId") Long userId);

}
