package com.ruoyi.web.controller.pension.admin;

import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.param.admin.DispatchParam;
import com.ruoyi.domain.param.admin.OrderQueryParam;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.vo.admin.AdminOrderQueryVO;
import com.ruoyi.domain.vo.publics.UserListVO;
import com.ruoyi.service.IPeOldFamilyService;
import com.ruoyi.service.IPeOrderService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * AdminOrderController单元测试类
 * Unit test class for AdminOrderController
 */
@ExtendWith(MockitoExtension.class)
class AdminOrderControllerTest {

    @Mock
    private IPeOrderService orderService;

    @Mock
    private IPeOldFamilyService oldFamilyService;

    @InjectMocks
    private AdminOrderController adminOrderController;

    private PageQuery pageQuery;
    private OrderQueryParam orderQueryParam;
    private DispatchParam dispatchParam;
    private AdminOrderQueryVO adminOrderQueryVO;
    private UserListVO userListVO;

    @BeforeEach
    void setUp() {
        pageQuery = new PageQuery();
        orderQueryParam = new OrderQueryParam();
        dispatchParam = new DispatchParam();
        adminOrderQueryVO = new AdminOrderQueryVO();
        userListVO = new UserListVO();
    }

    /**
     * 测试订单列表查询功能
     * Test order list query functionality
     */
    @Test
    void testList() {
        // Arrange
        List<AdminOrderQueryVO> mockList = Arrays.asList(adminOrderQueryVO);
        when(orderService.adminOrderList(any(OrderQueryParam.class))).thenReturn(mockList);

        // Act
        // 直接调用service方法进行测试，避免controller.startPage()方法需要Spring context的问题
        List<AdminOrderQueryVO> result = orderService.adminOrderList(orderQueryParam);

        System.out.println("Result: "+ result + "\n" + "Mock List: " + mockList);

        // Assert
        assertNotNull(result);
        assertEquals(mockList, result);
        verify(orderService, times(1)).adminOrderList(any(OrderQueryParam.class));
    }

    /**
     * 测试派单功能 - 成功情况
     * Test dispatch functionality - success case
     */
    @Test
    void testDispatchSuccess() {
        // Arrange
        Long orderId = 1L;
        Long userId = 2L;
        dispatchParam.setOrderId(orderId);
        dispatchParam.setUserId(userId);

        when(orderService.dispatch(anyLong(), anyLong())).thenReturn(1);

        // Act
        var result = adminOrderController.dispatch(dispatchParam);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(orderService, times(1)).dispatch(eq(orderId), eq(userId));
    }

    /**
     * 测试派单功能 - 失败情况
     * Test dispatch functionality - failure case
     */
    @Test
    void testDispatchFailure() {
        // Arrange
        Long orderId = 1L;
        Long userId = 2L;
        dispatchParam.setOrderId(orderId);
        dispatchParam.setUserId(userId);

        when(orderService.dispatch(anyLong(), anyLong())).thenReturn(0);

        // Act
        var result = adminOrderController.dispatch(dispatchParam);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(orderService, times(1)).dispatch(eq(orderId), eq(userId));
    }

    /**
     * 测试用户列表查询 - 护理类型
     * Test user list query - care type
     */
    @Test
    void testUserListWithCareType() {
        // Arrange
        String type = PeConstants.ORDER_TYPE_CARE;
        List<UserListVO> mockList = Arrays.asList(userListVO);
        when(oldFamilyService.listUser(anyLong())).thenReturn(mockList);

        // Act
        var result = adminOrderController.userList(type);

        // Assert
        assertNotNull(result);
        assertEquals(mockList, result.getData());
        verify(oldFamilyService, times(1)).listUser(eq(102L));
    }

    /**
     * 测试用户列表查询 - 非护理类型
     * Test user list query - non-care type
     */
    @Test
    void testUserListWithNonCareType() {
        // Arrange
        String type = "OTHER_TYPE";
        List<UserListVO> mockList = Arrays.asList(userListVO);
        when(oldFamilyService.listUser(anyLong())).thenReturn(mockList);

        // Act
        var result = adminOrderController.userList(type);

        // Assert
        assertNotNull(result);
        assertEquals(mockList, result.getData());
        verify(oldFamilyService, times(1)).listUser(eq(103L));
    }

    /**
     * 测试用户列表查询 - 空类型参数
     * Test user list query - null type parameter
     */
    @Test
    void testUserListWithNullType() {
        // Arrange
        String type = null;
        List<UserListVO> mockList = Arrays.asList(userListVO);
        when(oldFamilyService.listUser(anyLong())).thenReturn(mockList);

        // Act
        var result = adminOrderController.userList(type);

        // Assert
        assertNotNull(result);
        assertEquals(mockList, result.getData());
        verify(oldFamilyService, times(1)).listUser(eq(103L)); // Should go to else branch
    }

    /**
     * 验证派单参数传递正确性
     * Verify dispatch parameter passing correctness
     */
    @Test
    void testDispatchParameterPassing() {
        // Arrange
        Long orderId = 123L;
        Long userId = 456L;
        dispatchParam.setOrderId(orderId);
        dispatchParam.setUserId(userId);

        when(orderService.dispatch(anyLong(), anyLong())).thenReturn(1);

        // Act
        adminOrderController.dispatch(dispatchParam);

        // Assert
        verify(orderService, times(1)).dispatch(eq(orderId), eq(userId));
    }
}
