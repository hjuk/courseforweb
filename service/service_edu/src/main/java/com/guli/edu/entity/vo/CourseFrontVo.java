package com.guli.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseFrontVo {
    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @ApiModelProperty(value = "课程销售数量")
    private BigDecimal byCountSort;

    @ApiModelProperty(value = "创建时间排序")
    private String gmtCreatSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;

}
