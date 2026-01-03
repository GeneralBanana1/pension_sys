package com.ruoyi.web.controller.pension.admin;

import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.param.admin.OrderQueryParam;
import com.ruoyi.domain.param.admin.DispatchParam;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.vo.admin.AdminOrderQueryVO;
import com.ruoyi.domain.vo.publics.UserListVO;
import com.ruoyi.service.IPeOldFamilyService;
import com.ruoyi.service.IPeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "管理-订单管理")
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {

    @Autowired
    private IPeOrderService orderService;

    @Autowired
    private IPeOldFamilyService oldFamilyService;

    @ApiOperation("订单列表")
    @GetMapping("/list")
    public TableDataInfo list(PageQuery query, OrderQueryParam param){
        startPage();
        List<AdminOrderQueryVO> list = orderService.adminOrderList(param);
        return getDataTable(list);
    }

    @ApiOperation("派单")
    @PutMapping("/dispatch")
    public R dispatch(@RequestBody DispatchParam param){
        Long orderId = param.getOrderId();
        Long userId = param.getUserId();
        return R.to(orderService.dispatch(orderId,userId));
    }

    @ApiOperation("查询员工列表")
    @GetMapping("/userList")
    public R userList(String type){

        System.out.println(type);

        List<UserListVO> list = null;

        if (PeConstants.ORDER_TYPE_CARE.equals(type)){
            list = oldFamilyService.listUser(102l);
        }else {
            list = oldFamilyService.listUser(103l);
        }

        return R.ok(list);
    }


}
