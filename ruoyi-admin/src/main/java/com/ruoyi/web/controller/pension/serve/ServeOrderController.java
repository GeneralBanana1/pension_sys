package com.ruoyi.web.controller.pension.serve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.vo.service.ServiceOrderQueryVO;
import com.ruoyi.domain.vo.service.ServiceUpdateStateParam;
import com.ruoyi.domain.vo.user.UserOrderQueryVO;
import com.ruoyi.service.IPeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "服务-订单管理")
@RestController
@RequestMapping("/service/order")
public class ServeOrderController extends BaseController {

    @Autowired
    private IPeOrderService orderService;

    @ApiOperation("订单列表")
    @GetMapping("/list")
    public TableDataInfo list(@Validated PageQuery query,
                              @ApiParam("订单状态") String state){
        startPage();
        List<ServiceOrderQueryVO> list = orderService.serviceOrderList(getUserId(), state);
        return getDataTable(list);
    }

    @ApiOperation("修改订单状态")
    @PutMapping("/updateState")
    public R updateState(@ApiParam("修改订单状态参数") @Validated ServiceUpdateStateParam param){

        Long orderId = param.getOrderId();
        String state = param.getState();
        LambdaQueryWrapper<PeOrder> query = new LambdaQueryWrapper<>();
        query.eq(PeOrder::getOrderId, orderId);
        PeOrder order = orderService.getOne(query);
       order.setState(state);

       return R.to(orderService.updateById(order));
    }


}
