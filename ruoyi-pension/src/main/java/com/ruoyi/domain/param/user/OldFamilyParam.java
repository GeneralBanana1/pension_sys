package com.ruoyi.domain.param.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("绑定老人亲属关系参数")
@Data
public class OldFamilyParam {

    private Long oldId;

    private Long familyId;

    private String relationship;

}
