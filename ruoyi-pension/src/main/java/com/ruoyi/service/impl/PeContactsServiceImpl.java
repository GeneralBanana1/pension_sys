package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.PeConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeContacts;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.param.user.PeContactsParam;
import com.ruoyi.domain.vo.user.PeContactsQueryVO;
import com.ruoyi.mapper.PeContactsMapper;
import com.ruoyi.mapping.PeContactsMapping;
import com.ruoyi.service.IPeContactsService;
import org.apache.ibatis.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.constant.PeConstants.CONTACTS_IS_DEFAULT_YES;

@Service
public class PeContactsServiceImpl extends ServiceImpl<PeContactsMapper, PeContacts>
        implements IPeContactsService {

    @Autowired
    private PeContactsMapper contactsMapper;


    @Override
    public List<PeContactsQueryVO> list(Long customerId, PageQuery query, String isDefault) {
        QueryWrapper queryWrapper  = new QueryWrapper();
        queryWrapper.eq(customerId!= null,"user_id",customerId);
        queryWrapper.ne("pe_contacts.del_flag", PeConstants.DEL);
        queryWrapper.eq(isDefault!= null,"is_default",isDefault);

        List<PeContacts> peContactsList = contactsMapper.selectList(queryWrapper);
        List<PeContactsQueryVO> peContactsQueryVOList = PeContactsMapping.INSTANCE.toPeContactsQueryVOList(peContactsList);
        return peContactsQueryVOList;


    }

    @Override
    public R addContacts(PeContactsParam peContactsParam) {
        PeContacts peContacts = PeContactsMapping.INSTANCE.DtoToPo(peContactsParam);
        int insert = contactsMapper.insert(peContacts);
          if ( insert > 0)
             return R.ok();
         else
             return R.fail("添加失败");
    }

    @Override
    public R updateContacts(PeContactsParam peContactsParam) {
        PeContacts peContacts = PeContactsMapping.INSTANCE.DtoToPo(peContactsParam);
        int i = contactsMapper.updateById(peContacts);
        if (  i > 0)
             return R.ok();
        else
            return R.fail("修改失败");
    }
    @Override
    public R deleteContacts(Long contactsId){
        int delete = contactsMapper.deleteById( contactsId);
        if ( delete > 0){
            return  R.ok();
        }else
            return R.fail("删除失败");
    }

}
