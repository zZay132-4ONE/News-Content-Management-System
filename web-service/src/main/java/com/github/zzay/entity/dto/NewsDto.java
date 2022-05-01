package com.github.zzay.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zzay
 * @className NewsDto
 * @description News DTO
 * @create 2022/05/01 00:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto implements Serializable {

    private static final long serialVersionUID = -7900309316831067955L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "新闻唯一标识符")
    private Integer id;

    /**
     * Title
     */
    @Schema(name = "title", description = "新闻标题")
    private String title;

    /**
     * Description
     */
    @Schema(name = "description", description = "新闻简介")
    private String description;

    /**
     * Contents
     */
    @Schema(name = "contents", description = "新闻内容")
    private String contents;

}
