package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.vo.admin.AdminOrderQueryVO;
import com.ruoyi.domain.vo.service.ServiceOrderQueryVO;
import com.ruoyi.domain.vo.user.UserOrderQueryVO;

import java.util.List;

public interface IPeOrderService extends IService<PeOrder> {

    List<UserOrderQueryVO> userOrderList(Long customerId, String state, String type);

    List<ServiceOrderQueryVO> serviceOrderList(Long serviceId, String state);

    List<AdminOrderQueryVO>  adminOrderList(String state);

    int dispatch(Long orderId,Long userId);
}
