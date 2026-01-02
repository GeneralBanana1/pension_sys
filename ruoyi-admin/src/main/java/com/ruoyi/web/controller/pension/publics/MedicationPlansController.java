package com.ruoyi.web.controller.pension.publics;

import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.entity.PeMedicationPlans;
import com.ruoyi.domain.param.publics.PeMedicationPlansCreateParam;
import com.ruoyi.domain.param.publics.PeMedicationPlansUpdateParam;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.vo.publics.MedicationPlansListVO;
import com.ruoyi.mapping.PeMedicationPlansMapping;
import com.ruoyi.service.IPeMedicationPlansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "公共-用药计划")
@RestController
@RequestMapping("/medication/plans")
public class MedicationPlansController extends BaseController {

    @Autowired
    private IPeMedicationPlansService medicationPlansService;

    @ApiOperation("用药计划列表")
    @GetMapping("/list")
    public TableDataInfo list(PageQuery query,Long userId) {
        startPage();
        List<MedicationPlansListVO> list = medicationPlansService.list(userId);
        return getDataTable(list);
    }


    @ApiOperation("新建用药计划")
    @PostMapping("/create")
    public R create(@Validated @RequestBody PeMedicationPlansCreateParam param) {
        return R.to(medicationPlansService.save(PeMedicationPlansMapping.INSTANCE.to(param)));
    }

    @ApiOperation("更新用药计划")
    @PutMapping("/update")
    public R update(@Validated @RequestBody PeMedicationPlansUpdateParam param) {
        return R.to(medicationPlansService.updateById(PeMedicationPlansMapping.INSTANCE.to(param)));
    }

    @ApiOperation("禁用用药计划")
    @PutMapping("/disable/{medicationPlansId}")
    public R disable(@PathVariable Long medicationPlansId){
        PeMedicationPlans byId = medicationPlansService.getById(medicationPlansId);
        byId.setIsActive(PeConstants.MEDICATION_PLANS_INACTIVE);
        return R.to(medicationPlansService.updateById(byId));
    }

    @ApiOperation("启用用药计划")
    @PutMapping("/enable/{medicationPlansId}")
    public R enable(@PathVariable Long medicationPlansId){
        PeMedicationPlans byId = medicationPlansService.getById(medicationPlansId);
        byId.setIsActive(PeConstants.MEDICATION_PLANS_ACTIVE);
        return R.to(medicationPlansService.updateById(byId));
    }


    @ApiOperation("删除用药计划")
    @PutMapping("/delete/{medicationPlansId}")
    public R delete(@PathVariable Long medicationPlansId){
        PeMedicationPlans byId = medicationPlansService.getById(medicationPlansId);
        byId.setDelFlag(PeConstants.DEL);
        return R.to(medicationPlansService.updateById(byId));
    }

}
