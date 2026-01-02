package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeHealthCondition;
import com.ruoyi.domain.param.user.PeHealthConditionParam;
import com.ruoyi.service.IPeHealthConditionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api(tags = "健康信息管理")
@RestController
@RequestMapping("/healthCondition")
public class HealthConditionController {
    @Autowired
    private IPeHealthConditionService pehealthConditionService;
    @GetMapping("/select")
    public R listHealthCondition(@RequestParam Long customerId){
        return pehealthConditionService.selectById(customerId);
    }
    @PostMapping("/add")
    public R addHealthCondition(@RequestBody PeHealthConditionParam peHealthConditionParam){
        return pehealthConditionService.addHealthCondition(peHealthConditionParam);
    }
    @PutMapping("/update")
    public R updateHealthCondition(@RequestBody PeHealthConditionParam peHealthConditionParam){
        return pehealthConditionService.updateHealthCondition(peHealthConditionParam);
    }
}
