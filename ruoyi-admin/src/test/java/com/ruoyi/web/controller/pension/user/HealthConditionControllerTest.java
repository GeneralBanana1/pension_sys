package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.param.user.PeHealthConditionParam;
import com.ruoyi.service.IPeHealthConditionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * HealthConditionController单元测试类
 * Unit test class for HealthConditionController
 */
@ExtendWith(MockitoExtension.class)
class HealthConditionControllerTest {

    @Mock
    private IPeHealthConditionService healthConditionService;

    @InjectMocks
    private HealthConditionController healthConditionController;

    /**
     * 测试前初始化
     * Initialize test data before each test
     */
    @BeforeEach
    void setUp() {
        // 初始化测试数据（如果需要）
    }

    /**
     * 测试从Redis查询健康信息 - 成功情况
     * Test select health condition from Redis - success case
     */
    @Test
    void testSelectFromRedis_Success() {
        // Arrange
        Long customerId = 1L;
        R expectedResult = R.ok("健康信息");

        when(healthConditionService.selectFromRedis(eq(customerId))).thenReturn(expectedResult);

        // Act
        R result = healthConditionController.selectFromRedis(customerId);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        assertEquals(expectedResult, result);
        verify(healthConditionService, times(1)).selectFromRedis(eq(customerId));
    }

    /**
     * 测试从MySQL查询健康信息 - 成功情况
     * Test select health condition from MySQL - success case
     */
    @Test
    void testSelectFromMysql_Success() {
        // Arrange
        Long customerId = 1L;
        R expectedResult = R.ok("健康信息");

        when(healthConditionService.selectFromMysql(eq(customerId))).thenReturn(expectedResult);

        // Act
        R result = healthConditionController.selectFromMysql(customerId);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        assertEquals(expectedResult, result);
        verify(healthConditionService, times(1)).selectFromMysql(eq(customerId));
    }

    /**
     * 测试添加健康信息 - 成功情况
     * Test add health condition - success case
     */
    @Test
    void testAddHealthCondition_Success() {
        // Arrange
        PeHealthConditionParam param = new PeHealthConditionParam();
        param.setUserId(1L);
        param.setBloodPressure("120/80");
        param.setFastingBloodSugar("5.6");

        when(healthConditionService.addHealthCondition(any(PeHealthConditionParam.class))).thenReturn(R.ok());

        // Act
        R result = healthConditionController.addHealthCondition(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(healthConditionService, times(1)).addHealthCondition(any(PeHealthConditionParam.class));
    }

    /**
     * 测试添加健康信息 - 失败情况
     * Test add health condition - failure case
     */
    @Test
    void testAddHealthCondition_Failure() {
        // Arrange
        PeHealthConditionParam param = new PeHealthConditionParam();
        param.setUserId(1L);
        param.setBloodPressure("120/80");
        param.setFastingBloodSugar("5.6");

        when(healthConditionService.addHealthCondition(any(PeHealthConditionParam.class))).thenReturn(R.fail());

        // Act
        R result = healthConditionController.addHealthCondition(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(healthConditionService, times(1)).addHealthCondition(any(PeHealthConditionParam.class));
    }

    /**
     * 测试修改健康信息 - 成功情况
     * Test update health condition - success case
     */
    @Test
    void testUpdateHealthCondition_Success() {
        // Arrange
        PeHealthConditionParam param = new PeHealthConditionParam();
        param.setUserId(1L);
        param.setBloodPressure("130/85");
        param.setFastingBloodSugar("6.0");

        when(healthConditionService.updateHealthCondition(any(PeHealthConditionParam.class))).thenReturn(R.ok());

        // Act
        R result = healthConditionController.updateHealthCondition(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(healthConditionService, times(1)).updateHealthCondition(any(PeHealthConditionParam.class));
    }

    /**
     * 测试修改健康信息 - 失败情况
     * Test update health condition - failure case
     */
    @Test
    void testUpdateHealthCondition_Failure() {
        // Arrange
        PeHealthConditionParam param = new PeHealthConditionParam();
        param.setUserId(1L);
        param.setBloodPressure("130/85");
        param.setFastingBloodSugar("6.0");

        when(healthConditionService.updateHealthCondition(any(PeHealthConditionParam.class))).thenReturn(R.fail());

        // Act
        R result = healthConditionController.updateHealthCondition(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(healthConditionService, times(1)).updateHealthCondition(any(PeHealthConditionParam.class));
    }
}