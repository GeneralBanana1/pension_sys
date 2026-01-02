package com.ruoyi.mapping;

import com.ruoyi.domain.entity.PeContacts;
import com.ruoyi.domain.param.user.PeContactsParam;
import com.ruoyi.domain.vo.user.PeContactsQueryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PeContactsMapping {

    PeContactsMapping INSTANCE = Mappers.getMapper(PeContactsMapping.class);

    PeContactsParam PoToDto(PeContacts peContacts);

    PeContacts DtoToPo(PeContactsParam peContactsParam);

    PeContactsQueryVO toVo(PeContacts peContacts);

    List<PeContactsQueryVO> toPeContactsQueryVOList(List<PeContacts> peContactsList);
}
