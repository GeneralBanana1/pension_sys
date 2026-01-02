package com.ruoyi.domain.param.publics;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel("用药记录创建参数")
public class PeMedicationRecordsCreateParam {

    @ApiModelProperty("用药计划ID")
    @NotNull(message = "用药计划ID不能为空")
    private long medicationPlansId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("用药计划时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "用药计划时间不能为空")
    private Date time;


}
