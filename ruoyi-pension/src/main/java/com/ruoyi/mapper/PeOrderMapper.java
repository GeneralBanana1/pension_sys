package com.ruoyi.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.vo.admin.AdminOrderQueryVO;
import com.ruoyi.domain.vo.service.CustomerVo;
import com.ruoyi.domain.vo.service.ServiceOrderQueryVO;
import com.ruoyi.domain.vo.user.UserOrderQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PeOrderMapper extends BaseMapper<PeOrder> {

    List<UserOrderQueryVO> userOrderList(@Param("ew")Wrapper ew);

    List<ServiceOrderQueryVO> serviceOrderList(@Param("ew")Wrapper ew);

    List<AdminOrderQueryVO> adminOrderList(@Param("ew")Wrapper ew);

    List<CustomerVo> customerList(@Param("serviceId") Long serviceId);
}
