package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.domain.param.user.SetRoleParam;
import com.ruoyi.domain.param.user.OldFamilyParam;
import com.ruoyi.domain.vo.service.CustomerVo;
import com.ruoyi.domain.vo.user.OldVo;
import com.ruoyi.domain.vo.user.UserInfoVO;
import com.ruoyi.mapping.PeOldFamilyMapping;
import com.ruoyi.mapping.UserMapping;
import com.ruoyi.service.IPeOldFamilyService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/user/info")
public class UserInfoController extends BaseController {

    @Autowired
    private IPeOldFamilyService oldFamilyService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public R<UserInfoVO> get(){
        SysUser sysUser = userService.selectUserById(getUserId());
        return R.ok(UserMapping.INSTANCE.to(sysUser));
    }


    @ApiOperation("获取用户角色")
    @GetMapping("/role")
    public R getRole(){
        Long userId = getUserId();
        System.out.println("userId:"+userId);
        if (roleService.selectRoleListByUserId(userId).isEmpty()){
            return R.ok();
        }
        return R.ok(roleService.selectRoleListByUserId(userId).get(0));
    }

    @ApiOperation("设置用户角色")
    @PostMapping("/role")
    public AjaxResult insertAuthRole(@RequestBody SetRoleParam  param)
    {
        Long userId = param.getUserId();
        Long[] roleIds = new Long[]{param.getRoleId()};
        System.out.println("userId:"+userId);
        System.out.println("roleIds:"+roleIds);
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    @ApiOperation("绑定亲属与老人")
    @PostMapping("/old/family")
    public R addOldFamily(@RequestBody @Validated OldFamilyParam param){
        return R.to(oldFamilyService.save(PeOldFamilyMapping.INSTANCE.to(param)));
    }

    @ApiOperation("获取绑定的亲属与老人")
    @GetMapping("/old/family")
    public R<List<OldVo>> list(){
        return R.ok(oldFamilyService.list(getUserId()));
    }


}
