package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.domain.entity.PeOldFamily;
import com.ruoyi.domain.param.user.QueryOldParam;
import com.ruoyi.domain.param.user.SetRoleParam;
import com.ruoyi.domain.param.user.OldFamilyParam;
import com.ruoyi.domain.param.user.UpdateUserInfoParam;
import com.ruoyi.domain.vo.user.OldVo;
import com.ruoyi.domain.vo.user.UserInfoVO;
import com.ruoyi.service.IPeOldFamilyService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * UserInfoController单元测试类
 * Unit test class for UserInfoController
 */
@ExtendWith(MockitoExtension.class)
class UserInfoControllerTest {

    @Mock
    private IPeOldFamilyService oldFamilyService;

    @Mock
    private ISysUserService userService;

    @Mock
    private ISysRoleService roleService;

    @InjectMocks
    private UserInfoController userInfoController;

    private SysUser mockSysUser;
    private UserInfoVO mockUserInfoVO;
    private List<OldVo> mockOldVoList;
    private List<com.ruoyi.domain.vo.publics.UserListVO> mockOldList;
    private PeOldFamily mockOldFamily;

    /**
     * 测试前初始化
     * Initialize test data before each test
     */
    @BeforeEach
    void setUp() {
        // 初始化模拟系统用户数据
        mockSysUser = new SysUser();
        mockSysUser.setUserId(1L);
        mockSysUser.setUserName("testuser");
        mockSysUser.setNickName("测试用户");

        // 初始化模拟用户信息VO
        mockUserInfoVO = new UserInfoVO();
        mockUserInfoVO.setUserId(1L);
        mockUserInfoVO.setNickName("测试用户");

        // 初始化模拟老人VO列表
        OldVo oldVo1 = new OldVo();
        oldVo1.setOldId(1L);
        oldVo1.setNickName("老人1");
        mockOldVoList = Arrays.asList(oldVo1);

        // 初始化模拟老人列表
        com.ruoyi.domain.vo.publics.UserListVO userListVO = new com.ruoyi.domain.vo.publics.UserListVO();
        userListVO.setUserId(1L);
        userListVO.setNickName("测试用户");
        mockOldList = Arrays.asList(userListVO);

        // 初始化模拟老人亲属关系
        mockOldFamily = new PeOldFamily();
        mockOldFamily.setFamilyId(1L);
        mockOldFamily.setOldId(1L);
    }

    /**
     * 测试获取用户信息 - 成功情况
     * Test get user info - success case
     */
    @Test
    void testGetUserInfo_Success() {
        // Arrange
        Long userId = 1L;
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);
        loginUser.setUser(mockSysUser);

        // Mock SecurityUtils.getLoginUser方法返回loginUser
        try (MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getLoginUser).thenReturn(loginUser);
            when(userService.selectUserById(userId)).thenReturn(mockSysUser);

            // Act
            R<UserInfoVO> result = userInfoController.get();

            // Assert
            assertNotNull(result);
            assertTrue(R.isSuccess(result));
            verify(userService, times(1)).selectUserById(eq(userId));
        }
    }

    /**
     * 测试获取用户角色 - 有角色情况
     * Test get user role - with role case
     */
    @Test
    void testGetRole_WithRole() {
        // Arrange
        Long userId = 1L;
        List<Long> roles = Collections.singletonList(1L);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);

        // Mock SecurityUtils.getLoginUser方法返回loginUser
        try (MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getLoginUser).thenReturn(loginUser);
            when(roleService.selectRoleListByUserId(userId)).thenReturn(roles);

            // Act
            R result = userInfoController.getRole();

            // Assert
            assertNotNull(result);
            assertTrue(R.isSuccess(result));
            verify(roleService, times(2)).selectRoleListByUserId(eq(userId));
        }
    }

    /**
     * 测试获取用户角色 - 无角色情况
     * Test get user role - without role case
     */
    @Test
    void testGetRole_WithoutRole() {
        // Arrange
        Long userId = 1L;
        List<Long> roles = Collections.emptyList();
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);

        // Mock SecurityUtils.getLoginUser方法返回loginUser
        try (MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getLoginUser).thenReturn(loginUser);
            when(roleService.selectRoleListByUserId(userId)).thenReturn(roles);

            // Act
            R result = userInfoController.getRole();

            // Assert
            assertNotNull(result);
            assertTrue(R.isSuccess(result));
            verify(roleService, times(1)).selectRoleListByUserId(eq(userId));
        }
    }

    /**
     * 测试设置用户角色 - 成功情况
     * Test set user role - success case
     */
    @Test
    void testInsertAuthRole_Success() {
        // Arrange
        SetRoleParam param = new SetRoleParam();
        param.setUserId(1L);
        param.setRoleId(2L);

        // Act
        AjaxResult result = userInfoController.insertAuthRole(param);

        // Assert
        assertNotNull(result);
        assertTrue(result.isSuccess());
        verify(userService, times(1)).insertUserAuth(eq(1L), eq(new Long[]{2L}));
    }

    /**
     * 测试绑定亲属与老人 - 成功情况
     * Test add old family - success case
     */
    @Test
    void testAddOldFamily_Success() {
        // Arrange
        OldFamilyParam param = new OldFamilyParam();
        param.setFamilyId(1L);
        param.setOldId(1L);

        when(oldFamilyService.getOne(any())).thenReturn(null);
        when(oldFamilyService.save(any(PeOldFamily.class))).thenReturn(true);

        // Act
        R result = userInfoController.addOldFamily(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(oldFamilyService, times(1)).getOne(any());
        verify(oldFamilyService, times(1)).save(any(PeOldFamily.class));
    }

    /**
     * 测试绑定亲属与老人 - 已绑定情况
     * Test add old family - already bound case
     */
    @Test
    void testAddOldFamily_AlreadyBound() {
        // Arrange
        OldFamilyParam param = new OldFamilyParam();
        param.setFamilyId(1L);
        param.setOldId(1L);

        when(oldFamilyService.getOne(any())).thenReturn(mockOldFamily);

        // Act
        R result = userInfoController.addOldFamily(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(oldFamilyService, times(1)).getOne(any());
        verify(oldFamilyService, never()).save(any(PeOldFamily.class));
    }

    /**
     * 测试解绑亲属与老人 - 成功情况
     * Test delete old family - success case
     */
    @Test
    void testDeletOldFamily_Success() {
        // Arrange
        Long oldId = 1L;
        Long familyId = 1L;
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(familyId);

        // Mock SecurityUtils.getLoginUser方法返回loginUser
        try (MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getLoginUser).thenReturn(loginUser);
            when(oldFamilyService.remove(any())).thenReturn(true);

            // Act
            R result = userInfoController.deletOldFamily(oldId);

            // Assert
            assertNotNull(result);
            assertTrue(R.isSuccess(result));
            verify(oldFamilyService, times(1)).remove(any());
        }
    }

    /**
     * 测试获取绑定的亲属与老人 - 成功情况
     * Test get bound old family - success case
     */
    @Test
    void testListBoundOldFamily_Success() {
        // Arrange
        Long familyId = 1L;
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(familyId);

        // Mock SecurityUtils.getLoginUser方法返回loginUser
        try (MockedStatic<SecurityUtils> mockedStatic = mockStatic(SecurityUtils.class)) {
            mockedStatic.when(SecurityUtils::getLoginUser).thenReturn(loginUser);
            when(oldFamilyService.list(familyId)).thenReturn(mockOldVoList);

            // Act
            R<List<OldVo>> result = userInfoController.list();

            // Assert
            assertNotNull(result);
            assertTrue(R.isSuccess(result));
            assertEquals(mockOldVoList, result.getData());
            verify(oldFamilyService, times(1)).list(eq(familyId));
        }
    }

    /**
     * 测试获取老人列表 - 成功情况
     * Test get old list - success case
     */
    @Test
    void testListOld_Success() {
        // Arrange
        QueryOldParam param = new QueryOldParam();
        param.setPhonenumber("13800138000");
        param.setNickName("测试");

        when(oldFamilyService.listOld(any())).thenReturn(mockOldList);

        // Act
        R result = userInfoController.list(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        assertEquals(mockOldList, result.getData());
        verify(oldFamilyService, times(1)).listOld(any());
    }

    /**
     * 测试修改用户信息 - 成功情况
     * Test update user info - success case
     */
    @Test
    void testUpdateUserInfo_Success() {
        // Arrange
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setUserId(1L);
        param.setNickName("更新后的昵称");

        when(userService.updateUserProfile(any(SysUser.class))).thenReturn(1);

        // Act
        R result = userInfoController.update(param);

        // Assert
        assertNotNull(result);
        assertTrue(R.isSuccess(result));
        verify(userService, times(1)).updateUserProfile(any(SysUser.class));
    }

    /**
     * 测试修改用户信息 - 失败情况
     * Test update user info - failure case
     */
    @Test
    void testUpdateUserInfo_Failure() {
        // Arrange
        UpdateUserInfoParam param = new UpdateUserInfoParam();
        param.setUserId(1L);
        param.setNickName("更新后的昵称");

        when(userService.updateUserProfile(any(SysUser.class))).thenReturn(0);

        // Act
        R result = userInfoController.update(param);

        // Assert
        assertNotNull(result);
        assertFalse(R.isSuccess(result));
        verify(userService, times(1)).updateUserProfile(any(SysUser.class));
    }
}