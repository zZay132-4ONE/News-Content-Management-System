package com.github.zzay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzay
 * @className Menu
 * @description Menu
 * @create 2022/04/30 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "menu")
@Schema(name = "Menu", description = "菜单实体")
public class Menu implements Serializable {

    private static final long serialVersionUID = 51618427342448681L;

    /**
     * ID (PK)
     */
    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "菜单唯一标识符")
    private Long id;

    /**
     * Name
     */
    @Schema(name = "name", description = "菜单名")
    private String name;

    /**
     * URL
     */
    @Schema(name = "url", description = "路由地址")
    private String url;

    /**
     * Permission
     */
    @Schema(name = "permission", description = "权限标识")
    private String permission;

    /**
     * Parent ID
     */
    @Schema(name = "parentid", description = "父菜单ID")
    private Long parentid;

    /**
     * Component path
     */
    @Schema(name = "component", description = "组件路径")
    private String component;

    /**
     * Menu visibility (Visible: 0 / Invisible: 1)
     */
    @Schema(name = "visible", description = "菜单状态（0显示 1隐藏）")
    private String visible;

    /**
     * Menu status (normal: 0 / abandoned: 1)
     */
    @Schema(name = "status", description = "菜单状态（0正常 1停用）")
    private String status;

    /**
     * Icon
     */
    @Schema(name = "icon", description = "菜单图标")
    private String icon;

    /**
     * Remark
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

    /**
     * Creator
     */
    @Hidden
    @Schema(name = "createBy", description = "创建人")
    private Long createBy;

    /**
     * Created time
     */
    @Hidden
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * Updater
     */
    @Hidden
    @Schema(name = "updateBy", description = "更新人")
    private Long updateBy;

    /**
     * Updated time
     */
    @Hidden
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * Delete flag (not deleted: 0 / deleted: 1)
     */
    @Hidden
    @Schema(name = "delFlag", description = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

}
