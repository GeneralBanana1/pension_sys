package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.domain.entity.PeContacts;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.param.user.PeContactsParam;
import com.ruoyi.domain.vo.user.PeContactsQueryVO;

import java.util.List;

public interface IPeContactsService extends IService<PeContacts> {
    List<PeContactsQueryVO> list(Long customerId, PageQuery query, String isDefault);

    R addContacts(PeContactsParam peContactsParam);

    R updateContacts(PeContactsParam peContactsParam);

    R deleteContacts(Long contactsId);
}
