package com.ruoyi.domain.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("分页查询参数")
public class PageQuery {

    @ApiModelProperty("页码")
    @NotNull(message = "页码不能为空")
    private Long pageNum;

    @ApiModelProperty("每页数量")
    @NotNull(message = "每页数量不能为空")
    private Long pageSize;

}
