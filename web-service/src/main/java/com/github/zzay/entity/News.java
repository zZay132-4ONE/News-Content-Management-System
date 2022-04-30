package com.github.zzay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zzay
 * @className News
 * @description News实体类
 * @create 2022/05/01 00:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("news")
@Schema(name = "News", description = "新闻实体")
public class News implements Serializable {

    private static final long serialVersionUID = -1087943348506479123L;

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

    /**
     * Is published (published: 1 / not published: 0)
     */
    @Schema(name = "published", description = "新闻是否发布（已发布：1 / 未发布：0）")
    private Integer published;

}
