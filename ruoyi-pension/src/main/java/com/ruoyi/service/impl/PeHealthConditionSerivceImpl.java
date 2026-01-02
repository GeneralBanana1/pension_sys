package com.ruoyi.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeHealthCondition;
import com.ruoyi.domain.param.user.PeHealthConditionParam;
import com.ruoyi.mapper.PeHealthConditionMapper;
import com.ruoyi.mapping.PeHealthConditionMapping;
import com.ruoyi.service.IPeHealthConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ruoyi.common.constant.PeConstants.Health;

@Service
public class PeHealthConditionSerivceImpl extends ServiceImpl<PeHealthConditionMapper, PeHealthCondition>
        implements IPeHealthConditionService {

    @Autowired
    private PeHealthConditionMapper healthConditionMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public R selectById(Long customerId) {
        if (customerId == null)
            return R.fail("老人id不能为空");
        Set<String> keys = redisTemplate.keys("*" + customerId);
        List<PeHealthConditionParam> list = new ArrayList<>();
        for (String key : keys) {
            String s = redisTemplate.opsForValue().get(key);
            PeHealthConditionParam peHealthConditionParam = JSONObject.parseObject(s, PeHealthConditionParam.class);
            list.add(peHealthConditionParam);
        }
        if (list.isEmpty()){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id",customerId);
            PeHealthCondition peHealthCondition = healthConditionMapper.selectOne(queryWrapper);
            if (peHealthCondition==null){
                return R.fail("老人不存在");
            }else{
                PeHealthConditionParam peHealthConditionParam = PeHealthConditionMapping.INSTANCE.POToDTO(peHealthCondition);
                saveToRedis(peHealthConditionParam);
                return R.ok(peHealthConditionParam);
            }
        }
        return R.ok(list);
    }

    @Override
    public R addHealthCondition(PeHealthConditionParam peHealthConditionParam) {
        PeHealthCondition peHealthCondition = PeHealthConditionMapping.INSTANCE.DTOToPO(peHealthConditionParam);
        int insert = healthConditionMapper.insert(peHealthCondition);
        if (insert >0){
            saveToRedis(peHealthConditionParam);
            return R.ok();
        } else {
          return R.fail("添加失败");
        }
    }

    @Override
    public R updateHealthCondition(PeHealthConditionParam peHealthConditionParam) {
        Long userId = peHealthConditionParam.getUserId();
        QueryWrapper<PeHealthCondition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        PeHealthCondition peHealthCondition = healthConditionMapper.selectOne(queryWrapper);
        if (peHealthCondition==null){
            return R.fail("老人不存在");
        }else{
            saveToRedis(peHealthConditionParam);
            PeHealthCondition peHealthCondition2 = PeHealthConditionMapping.INSTANCE.DTOToPO(peHealthConditionParam);
            healthConditionMapper.updateHealth(peHealthCondition2);
            return R.ok();
        }
    }

    private void saveToRedis(PeHealthConditionParam peHealthConditionParam){
        Long userId = peHealthConditionParam.getUserId();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
        String formattedDate = now.format(formatter);
        peHealthConditionParam.setCreate_time(formattedDate);
        String key = new String(Health+formattedDate+":"+userId);
        String jsonString = JSONObject.toJSONString(peHealthConditionParam);
        redisTemplate.opsForValue().set(key,jsonString);
    }

}
