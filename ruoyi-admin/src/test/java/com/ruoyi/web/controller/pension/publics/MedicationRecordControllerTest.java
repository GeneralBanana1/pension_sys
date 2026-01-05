package com.ruoyi.web.controller.pension.publics;

import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeMedicationRecord;
import com.ruoyi.domain.param.publics.PeMedicationRecordsCreateParam;
import com.ruoyi.domain.param.publics.PeMedicationRecordsUpdateParam;
import com.ruoyi.service.IPeMedicationRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * MedicationRecordController单元测试类
 * Unit test class for MedicationRecordController
 */
@ExtendWith(MockitoExtension.class)
class MedicationRecordControllerTest {

    @Mock
    private IPeMedicationRecordService medicationRecordService;

    @InjectMocks
    private MedicationRecordController medicationRecordController;

    private PeMedicationRecord mockMedicationRecord;

    /**
     * 测试前初始化
     * Initialize test data before each test
     */
    @BeforeEach
    void setUp() {
        // 初始化模拟用药记录数据
        mockMedicationRecord = new PeMedicationRecord();
        mockMedicationRecord.setMedicationRecordId(1L);
        mockMedicationRecord.setMedicationPlansId(1L);
        mockMedicationRecord.setDelFlag(PeConstants.NORMAL);
    }

    /**
     * 测试新建用药记录 - 成功情况
     * Test create medication record - success case
     */
    @Test
    void testCreate_Success() {
        // Arrange
        PeMedicationRecordsCreateParam param = new PeMedicationRecordsCreateParam();
        param.setMedicationPlansId(1L);
        param.setRemark("测试备注");
        param.setTime(new java.util.Date());

        when(medicationRecordService.save(any(PeMedicationRecord.class))).thenReturn(true);

        // Act
        R result = medicationRecordController.create(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(medicationRecordService, times(1)).save(any(PeMedicationRecord.class));
    }

    /**
     * 测试新建用药记录 - 失败情况
     * Test create medication record - failure case
     */
    @Test
    void testCreate_Failure() {
        // Arrange
        PeMedicationRecordsCreateParam param = new PeMedicationRecordsCreateParam();
        param.setMedicationPlansId(1L);
        param.setRemark("测试备注");
        param.setTime(new java.util.Date());

        when(medicationRecordService.save(any(PeMedicationRecord.class))).thenReturn(false);

        // Act
        R result = medicationRecordController.create(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(medicationRecordService, times(1)).save(any(PeMedicationRecord.class));
    }

    /**
     * 测试更新用药记录 - 成功情况
     * Test update medication record - success case
     */
    @Test
    void testUpdate_Success() {
        // Arrange
        PeMedicationRecordsUpdateParam param = new PeMedicationRecordsUpdateParam();
        param.setMedicationRecordId(1L);
        param.setMedicationPlansId(1L);
        param.setRemark("更新备注");
        param.setTime(new java.util.Date());

        when(medicationRecordService.updateById(any(PeMedicationRecord.class))).thenReturn(true);

        // Act
        R result = medicationRecordController.update(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(medicationRecordService, times(1)).updateById(any(PeMedicationRecord.class));
    }

    /**
     * 测试更新用药记录 - 失败情况
     * Test update medication record - failure case
     */
    @Test
    void testUpdate_Failure() {
        // Arrange
        PeMedicationRecordsUpdateParam param = new PeMedicationRecordsUpdateParam();
        param.setMedicationRecordId(1L);
        param.setMedicationPlansId(1L);
        param.setRemark("更新备注");
        param.setTime(new java.util.Date());

        when(medicationRecordService.updateById(any(PeMedicationRecord.class))).thenReturn(false);

        // Act
        R result = medicationRecordController.update(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(medicationRecordService, times(1)).updateById(any(PeMedicationRecord.class));
    }

    /**
     * 测试删除用药记录 - 成功情况
     * Test delete medication record - success case
     */
    @Test
    void testDelete_Success() {
        // Arrange
        Long medicationRecordId = 1L;

        when(medicationRecordService.getById(medicationRecordId)).thenReturn(mockMedicationRecord);
        when(medicationRecordService.updateById(any(PeMedicationRecord.class))).thenReturn(true);

        // Act
        R result = medicationRecordController.delete(medicationRecordId);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(medicationRecordService, times(1)).getById(eq(medicationRecordId));
        verify(medicationRecordService, times(1)).updateById(any(PeMedicationRecord.class));
    }

    /**
     * 测试删除用药记录 - 失败情况
     * Test delete medication record - failure case
     */
    @Test
    void testDelete_Failure() {
        // Arrange
        Long medicationRecordId = 1L;

        when(medicationRecordService.getById(medicationRecordId)).thenReturn(mockMedicationRecord);
        when(medicationRecordService.updateById(any(PeMedicationRecord.class))).thenReturn(false);

        // Act
        R result = medicationRecordController.delete(medicationRecordId);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(medicationRecordService, times(1)).getById(eq(medicationRecordId));
        verify(medicationRecordService, times(1)).updateById(any(PeMedicationRecord.class));
    }

    /**
     * 测试删除用药记录 - 记录不存在情况
     * Test delete medication record - record not found case
     */
    @Test
    void testDelete_RecordNotFound() {
        // Arrange
        Long medicationRecordId = 1L;

        when(medicationRecordService.getById(medicationRecordId)).thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            medicationRecordController.delete(medicationRecordId);
        });

        verify(medicationRecordService, times(1)).getById(eq(medicationRecordId));
        verify(medicationRecordService, never()).updateById(any(PeMedicationRecord.class));
    }
}