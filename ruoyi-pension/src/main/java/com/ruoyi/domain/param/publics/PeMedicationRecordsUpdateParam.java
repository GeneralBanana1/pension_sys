package com.ruoyi.domain.param.publics;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("用药计划更新参数")
@Data
public class PeMedicationRecordsUpdateParam {


    @ApiModelProperty("用药计划记录ID")
    @NotNull(message = "用药计划记录ID不能为空")
    private long medicationRecordId;

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
