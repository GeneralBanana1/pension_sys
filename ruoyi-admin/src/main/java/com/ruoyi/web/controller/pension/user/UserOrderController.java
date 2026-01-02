package com.ruoyi.web.controller.pension.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.param.user.UserOrderCreateParam;
import com.ruoyi.domain.param.user.UserOrderUpdateParam;
import com.ruoyi.domain.vo.user.UserOrderQueryVO;
import com.ruoyi.mapping.PeOrderMapping;
import com.ruoyi.service.IPeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Api(tags = "客户-订单管理")
@RestController
@RequestMapping("/user/order")
public class UserOrderController extends BaseController {

    @Autowired
    private IPeOrderService orderService;

    @ApiOperation("订单列表")
    @GetMapping("/list")
    public TableDataInfo list(@Validated PageQuery query,
                              @ApiParam("订单状态") String state,
                              @ApiParam("订单类型") String type,
                              @ApiParam("老人id") Long customerId){
        startPage();
        List<UserOrderQueryVO> list = orderService.userOrderList(customerId, state, type);
        return getDataTable(list);
    }

    @ApiOperation("创建订单")
    @PostMapping("/create")
    public R create(@RequestBody @Validated UserOrderCreateParam param){

        PeOrder peOrder = PeOrderMapping.INSTANCE.to(param);
        peOrder.setState(PeConstants.ORDER_STATE_WAIT);
        peOrder.setDelFlag(PeConstants.NORMAL);
        String uuid = UUID.randomUUID().toString();
        peOrder.setOrderNum(uuid);
        peOrder.setServiceId(null);


        return R.to(orderService.save(peOrder));
    }

    @ApiOperation("更新订单")
    @PutMapping("/update")
    public R update(@RequestBody @Validated UserOrderUpdateParam param){

        Long orderId = param.getOrderId();
        LambdaQueryWrapper<PeOrder> query = new LambdaQueryWrapper<>();
        query.eq(PeOrder::getOrderId, orderId);
        PeOrder order = orderService.getOne(query);
        if (PeConstants.ORDER_STATE_HANDLE.equals(order.getState()) || PeConstants.ORDER_STATE_DISPATCH.equals(order.getState())){
            return R.fail("订单已被接取不可修改");
        }


        PeOrder peOrder = PeOrderMapping.INSTANCE.to(param);
        return R.to(orderService.updateById(peOrder));
    }

    @ApiOperation("取消订单")
    @PutMapping("/cancel/{orderId}")
    public R cancel(@PathVariable Long orderId){

        LambdaQueryWrapper<PeOrder> query = new LambdaQueryWrapper<>();
        query.eq(PeOrder::getOrderId, orderId);
        PeOrder order = orderService.getOne(query);
        if (PeConstants.ORDER_STATE_HANDLE.equals(order.getState()) || PeConstants.ORDER_STATE_DISPATCH.equals(order.getState())){

            return R.fail("订单已被接取不可取消");
        }
        order.setState(PeConstants.ORDER_STATE_CANCEL);
        return R.to(orderService.updateById(order));
    }

    @ApiOperation("删除订单")
    @PutMapping("/delete/{orderId}")
    public R delete(@PathVariable Long orderId){

        LambdaQueryWrapper<PeOrder> query = new LambdaQueryWrapper<>();
        query.eq(PeOrder::getOrderId, orderId);
        PeOrder order = orderService.getOne(query);
        if (PeConstants.ORDER_STATE_HANDLE.equals(order.getState()) || PeConstants.ORDER_STATE_DISPATCH.equals(order.getState())){
            return R.fail("订单已被接取不可删除");
        }
        order.setDelFlag(PeConstants.DEL);
        return R.to(orderService.updateById(order));
    }





}
