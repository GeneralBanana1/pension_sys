package com.ruoyi.web.controller.pension.admin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.vo.admin.AdminOrderQueryVO;
import com.ruoyi.domain.vo.service.ServiceOrderQueryVO;
import com.ruoyi.service.IPeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "管理-订单管理")
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {

    @Autowired
    private IPeOrderService orderService;

    @ApiOperation("订单列表")
    @GetMapping("/list")
    public TableDataInfo list(PageQuery query, String state){
        startPage();
        List<AdminOrderQueryVO> list = orderService.adminOrderList(state);
        return getDataTable(list);
    }

    @ApiOperation("派单")
    @GetMapping("/dispatch")
    public R dispatch(Long orderId, Long userId){
        return R.to(orderService.dispatch(orderId,userId));
    }

}
