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
 * @className Role
 * @description Role实体类
 * @create 2022/04/30 16:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "role")
@Schema(name = "Role", description = "角色实体")
public class Role implements Serializable {

    private static final long serialVersionUID = 4177942112824940331L;

    /**
     * ID (PK)
     */
    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "角色唯一标识符")
    private Long id;

    /**
     * Name
     */
    @Schema(name = "name", description = "角色名")
    private String name;

    /**
     * 角色权限
     */
    @Schema(name = "roleKey", description = "角色权限")
    private String roleKey;

    /**
     * Role status (normal: 0 / abandoned: 1)
     */
    @Schema(name = "status", description = "角色状态（0正常 1停用）")
    private String status;

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
