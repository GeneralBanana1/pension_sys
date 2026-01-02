package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeHealthCondition;
import com.ruoyi.domain.param.user.PeHealthConditionParam;
import com.ruoyi.service.IPeHealthConditionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api(tags = "健康信息管理")
@RestController
@RequestMapping("/healthCondition")
public class HealthConditionController {
    @Autowired
    private IPeHealthConditionService pehealthConditionService;
    @ApiOperation("查询健康信息/redis")
    @GetMapping("/selectFromRedis")
    public R selectFromRedis(@RequestParam Long customerId){
        return pehealthConditionService.selectFromRedis(customerId);
    }
    @ApiOperation("查询健康信息/mysql")
    @GetMapping("/selectFromMysql")
    public R selectFromMysql(@RequestParam Long customerId){
        return pehealthConditionService.selectFromMysql(customerId);
    }
    @ApiOperation("添加健康信息")
    @PostMapping("/add")
    public R addHealthCondition(@RequestBody PeHealthConditionParam peHealthConditionParam){
        return pehealthConditionService.addHealthCondition(peHealthConditionParam);
    }
    @ApiOperation("修改健康信息")
    @PutMapping("/update")
    public R updateHealthCondition(@RequestBody PeHealthConditionParam peHealthConditionParam){
        return pehealthConditionService.updateHealthCondition(peHealthConditionParam);
    }
}
