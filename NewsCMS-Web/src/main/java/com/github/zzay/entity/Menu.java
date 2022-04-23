package com.github.zzay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzay
 * @className Menu
 * @description 菜单实体
 * @create 2022/04/20 23:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "sys_menu")
@Schema(name = "Menu", description = "菜单实体")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1111625014132666993L;

    /**
     * ID
     */
    @TableId
    @Schema(name = "id", description = "菜单唯一标识符")
    private Long id;

    /**
     * 菜单名
     */
    @Schema(name = "menuName", description = "菜单名")
    private String menuName;

    /**
     * 路由地址
     */
    @Schema(name = "path", description = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @Schema(name = "component", description = "组件路径")
    private String component;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @Schema(name = "visible", description = "菜单状态（0显示 1隐藏）")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @Schema(name = "status", description = "菜单状态（0正常 1停用）")
    private String status;

    /**
     * 权限标识
     */
    @Schema(name = "perms", description = "权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @Schema(name = "remark", description = "备注")
    private String icon;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @Schema(name = "updateBy", description = "更新人")
    private Long updateBy;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @Schema(name = "delFlag", description = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

}
