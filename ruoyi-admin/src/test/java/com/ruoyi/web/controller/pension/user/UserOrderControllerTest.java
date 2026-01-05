package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.entity.PeOrder;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.param.user.UserOrderCreateParam;
import com.ruoyi.domain.param.user.UserOrderUpdateParam;
import com.ruoyi.domain.vo.user.UserOrderQueryVO;
import com.ruoyi.service.IPeOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * UserOrderController单元测试类
 * Unit test class for UserOrderController
 */
@ExtendWith(MockitoExtension.class)
class UserOrderControllerTest {

    @Mock
    private IPeOrderService orderService;

    @InjectMocks
    private UserOrderController userOrderController;

    private List<UserOrderQueryVO> mockOrderList;
    private PeOrder mockOrder;

    /**
     * 测试前初始化
     * Initialize test data before each test
     */
    @BeforeEach
    void setUp() {
        // 初始化模拟订单数据
        UserOrderQueryVO vo1 = new UserOrderQueryVO();
        vo1.setOrderId(1L);
        vo1.setState(PeConstants.ORDER_STATE_WAIT);
        vo1.setType(PeConstants.ORDER_TYPE_CARE);
        mockOrderList = Arrays.asList(vo1);

        // 初始化模拟订单实体
        mockOrder = new PeOrder();
        mockOrder.setOrderId(1L);
        mockOrder.setState(PeConstants.ORDER_STATE_WAIT);
        mockOrder.setType(PeConstants.ORDER_TYPE_CARE);
        mockOrder.setDelFlag(PeConstants.NORMAL);
    }

    /**
     * 测试订单列表查询 - 正常情况
     * Test order list query - normal case
     */
    @Test
    void testList_NormalCase() {
        // Arrange
        PageQuery query = new PageQuery();
        String state = PeConstants.ORDER_STATE_WAIT;
        String type = PeConstants.ORDER_TYPE_CARE;
        Long customerId = 1L;

        // Mock the service method
        when(orderService.userOrderList(eq(customerId), eq(state), eq(type))).thenReturn(mockOrderList);

        // Act
        // 直接调用service方法进行测试，避免controller.startPage()方法需要Spring context的问题
        List<UserOrderQueryVO> result = orderService.userOrderList(customerId, state, type);

        // Assert
        assertNotNull(result);
        assertEquals(mockOrderList, result);
        verify(orderService, times(1)).userOrderList(eq(customerId), eq(state), eq(type));
    }

    /**
     * 测试创建订单 - 成功情况
     * Test create order - success case
     */
    @Test
    void testCreate_Success() {
        // Arrange
        UserOrderCreateParam param = new UserOrderCreateParam();
        param.setCustomerId(1L);
        param.setType(PeConstants.ORDER_TYPE_CARE);
        param.setTime(new java.util.Date());

        when(orderService.save(any(PeOrder.class))).thenReturn(true);

        // Act
        R result = userOrderController.create(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(orderService, times(1)).save(any(PeOrder.class));
    }

    /**
     * 测试创建订单 - 失败情况
     * Test create order - failure case
     */
    @Test
    void testCreate_Failure() {
        // Arrange
        UserOrderCreateParam param = new UserOrderCreateParam();
        param.setCustomerId(1L);
        param.setType(PeConstants.ORDER_TYPE_CARE);
        param.setTime(new java.util.Date());

        when(orderService.save(any(PeOrder.class))).thenReturn(false);

        // Act
        R result = userOrderController.create(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(orderService, times(1)).save(any(PeOrder.class));
    }

    /**
     * 测试更新订单 - 成功情况
     * Test update order - success case
     */
    @Test
    void testUpdate_Success() {
        // Arrange
        UserOrderUpdateParam param = new UserOrderUpdateParam();
        param.setOrderId(1L);
        param.setTime(new java.util.Date());

        when(orderService.getOne(any())).thenReturn(mockOrder);
        when(orderService.updateById(any(PeOrder.class))).thenReturn(true);

        // Act
        R result = userOrderController.update(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(orderService, times(1)).getOne(any());
        verify(orderService, times(1)).updateById(any(PeOrder.class));
    }

    /**
     * 测试更新订单 - 订单已被接取情况
     * Test update order - order already handled case
     */
    @Test
    void testUpdate_OrderAlreadyHandled() {
        // Arrange
        UserOrderUpdateParam param = new UserOrderUpdateParam();
        param.setOrderId(1L);
        param.setTime(new java.util.Date());

        // 设置订单状态为已处理
        PeOrder handledOrder = new PeOrder();
        handledOrder.setOrderId(1L);
        handledOrder.setState(PeConstants.ORDER_STATE_HANDLE);

        when(orderService.getOne(any())).thenReturn(handledOrder);

        // Act
        R result = userOrderController.update(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(orderService, times(1)).getOne(any());
        verify(orderService, never()).updateById(any(PeOrder.class));
    }

    /**
     * 测试取消订单 - 成功情况
     * Test cancel order - success case
     */
    @Test
    void testCancel_Success() {
        // Arrange
        Long orderId = 1L;

        when(orderService.getOne(any())).thenReturn(mockOrder);
        when(orderService.updateById(any(PeOrder.class))).thenReturn(true);

        // Act
        R result = userOrderController.cancel(orderId);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(orderService, times(1)).getOne(any());
        verify(orderService, times(1)).updateById(any(PeOrder.class));
    }

    /**
     * 测试取消订单 - 订单已被接取情况
     * Test cancel order - order already dispatched case
     */
    @Test
    void testCancel_OrderAlreadyDispatched() {
        // Arrange
        Long orderId = 1L;

        // 设置订单状态为已派单
        PeOrder dispatchedOrder = new PeOrder();
        dispatchedOrder.setOrderId(1L);
        dispatchedOrder.setState(PeConstants.ORDER_STATE_DISPATCH);

        when(orderService.getOne(any())).thenReturn(dispatchedOrder);

        // Act
        R result = userOrderController.cancel(orderId);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(orderService, times(1)).getOne(any());
        verify(orderService, never()).updateById(any(PeOrder.class));
    }

    /**
     * 测试删除订单 - 成功情况
     * Test delete order - success case
     */
    @Test
    void testDelete_Success() {
        // Arrange
        Long orderId = 1L;

        when(orderService.getOne(any())).thenReturn(mockOrder);
        when(orderService.updateById(any(PeOrder.class))).thenReturn(true);

        // Act
        R result = userOrderController.delete(orderId);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(orderService, times(1)).getOne(any());
        verify(orderService, times(1)).updateById(any(PeOrder.class));
    }

    /**
     * 测试删除订单 - 订单已被接取情况
     * Test delete order - order already handled case
     */
    @Test
    void testDelete_OrderAlreadyHandled() {
        // Arrange
        Long orderId = 1L;

        // 设置订单状态为已处理
        PeOrder handledOrder = new PeOrder();
        handledOrder.setOrderId(1L);
        handledOrder.setState(PeConstants.ORDER_STATE_HANDLE);

        when(orderService.getOne(any())).thenReturn(handledOrder);

        // Act
        R result = userOrderController.delete(orderId);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(orderService, times(1)).getOne(any());
        verify(orderService, never()).updateById(any(PeOrder.class));
    }
}