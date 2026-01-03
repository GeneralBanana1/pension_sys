package com.ruoyi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.domain.entity.PeOldFamily;
import com.ruoyi.domain.vo.user.OldVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PeOldFamilyMapper extends BaseMapper<PeOldFamily> {

    List<OldVo> list(@Param("userId") Long userId);

}
