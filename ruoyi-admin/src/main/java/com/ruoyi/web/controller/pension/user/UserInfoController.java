package com.ruoyi.web.controller.pension.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.domain.entity.PeOldFamily;
import com.ruoyi.domain.param.user.QueryOldParam;
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

        Long familyId = param.getFamilyId();
        Long oldId = param.getOldId();

        LambdaQueryWrapper<PeOldFamily> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PeOldFamily::getFamilyId, familyId)
                .eq(PeOldFamily::getOldId, oldId);
        PeOldFamily one = oldFamilyService.getOne(lqw);
        if (one != null)
            return R.fail("已与该用户绑定");

        return R.to(oldFamilyService.save(PeOldFamilyMapping.INSTANCE.to(param)));
    }

    @ApiOperation("解绑亲属与老人")
    @DeleteMapping("/old/family/{oldId}")
    public R deletOldFamily(@PathVariable Long oldId){
        LambdaQueryWrapper<PeOldFamily> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PeOldFamily::getFamilyId, getUserId())
                .eq(PeOldFamily::getOldId, oldId);
        return R.to(oldFamilyService.remove(lqw));
    }

    @ApiOperation("获取绑定的亲属与老人")
    @GetMapping("/old/family")
    public R<List<OldVo>> list(){
        return R.ok(oldFamilyService.list(getUserId()));
    }

    @ApiOperation("获取老人列表")
    @GetMapping("/old/list")
    public R list(QueryOldParam param){
        String phonenumber = param.getPhonenumber();
        String nickName = param.getNickName();
        QueryWrapper qw = new QueryWrapper();
        qw.like(phonenumber != null, "phonenumber", phonenumber);
        qw.like(nickName != null, "nick_name", nickName);
        return R.ok(oldFamilyService.listOld(qw));
    }



}
