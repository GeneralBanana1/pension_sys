package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.vo.admin.AdminOrderQueryVO;
import com.ruoyi.domain.vo.service.CustomerVo;
import com.ruoyi.domain.vo.service.ServiceOrderQueryVO;
import com.ruoyi.domain.vo.user.UserOrderQueryVO;
import com.ruoyi.mapper.PeOrderMapper;
import com.ruoyi.service.IPeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeOrderServiceImpl extends ServiceImpl<PeOrderMapper, PeOrder>
        implements IPeOrderService {

    @Autowired
    private PeOrderMapper orderMapper;

    @Override
    public List<UserOrderQueryVO> userOrderList(Long customerId, String  state, String  type) {

        QueryWrapper qw = new QueryWrapper();
        qw.eq(customerId!= null,"customer_id",customerId);
        qw.ne("pe_order.del_flag", PeConstants.DEL);
        qw.eq(state!= null,"state",state);
        qw.eq(type!= null,"type",type);

        return orderMapper.userOrderList(qw);
    }

    @Override
    public List<ServiceOrderQueryVO> serviceOrderList(Long serviceId, String state) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("service_id",serviceId);
        qw.ne("pe_order.del_flag", PeConstants.DEL);
        qw.eq(state!= null,"state",state);

        return orderMapper.serviceOrderList(qw);
    }

    @Override
    public List<AdminOrderQueryVO> adminOrderList(String state) {

        QueryWrapper qw = new QueryWrapper();
        qw.ne("pe_order.del_flag", PeConstants.DEL);
        qw.eq(state!= null,"state",state);

        return orderMapper.adminOrderList(qw);
    }

    @Override
    public int dispatch(Long orderId, Long userId) {

        PeOrder peOrder = orderMapper.selectById(orderId);

        if (!PeConstants.ORDER_STATE_WAIT.equals(peOrder.getState()))
        {
            throw new RuntimeException("订单已被接取不可修改");
        }

        peOrder.setServiceId(userId);
        peOrder.setState(PeConstants.ORDER_STATE_DISPATCH);

        return orderMapper.updateById(peOrder);
    }

    @Override
    public List<CustomerVo> userList(Long serviceId) {
        return orderMapper.customerList(serviceId);
    }
}
