package com.ruoyi.web.controller.pension.publics;

import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeMedicationRecord;
import com.ruoyi.domain.param.publics.PeMedicationRecordsCreateParam;
import com.ruoyi.domain.param.publics.PeMedicationRecordsUpdateParam;
import com.ruoyi.mapping.PeMedicationRecordMapping;
import com.ruoyi.service.IPeMedicationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "公共-用药计划记录")
@RestController("/medication/records")
public class MedicationRecordController {

    @Autowired
    private IPeMedicationRecordService medicationRecordService;

    @ApiOperation("新建用药记录")
    @PostMapping("/create")
    public R create(@RequestBody @Validated PeMedicationRecordsCreateParam param){
        return R.to(medicationRecordService.save(PeMedicationRecordMapping.INSTANCE.to(param)));
    }

    @ApiOperation("更新用药记录")
    @PutMapping("/update")
    public R update(@RequestBody @Validated PeMedicationRecordsUpdateParam param){
        return R.to(medicationRecordService.updateById(PeMedicationRecordMapping.INSTANCE.to(param)));
    }

    @ApiOperation("删除用药记录")
    @PutMapping("/delete/{medicationRecordId}")
    public R delete(@PathVariable Long medicationRecordId){
        PeMedicationRecord byId = medicationRecordService.getById(medicationRecordId);
        byId.setDelFlag(PeConstants.DEL);
        return R.to(medicationRecordService.updateById(byId));
    }

}
