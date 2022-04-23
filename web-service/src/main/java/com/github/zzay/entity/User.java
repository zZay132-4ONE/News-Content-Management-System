package com.github.zzay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzay
 * @className User
 * @description 用户实体
 * @create 2022/04/21 14:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user")
@Schema(name = "User", description = "用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = -6690897723869303541L;

    /**
     * 主键
     */
    @TableId
    @Schema(name = "id", description = "用户唯一标识符")
    private Long id;

    /**
     * 用户名
     */
    @Schema(name = "username", description = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
    private String password;

    /**
     * 账号状态（0正常 1停用）
     */
    @Schema(name = "status", description = "账号状态（0正常 1停用）")
    private String status;

    /**
     * 邮箱
     */
    @Schema(name = "email", description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(name = "phone", description = "手机号")
    private String phone;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @Schema(name = "sex", description = "用户性别（0男，1女，2未知）")
    private String sex;

    /**
     * 头像
     */
    @Schema(name = "avatar", description = "头像")
    private String avatar;

    /**
     * 用户类型（0管理员，1普通用户）
     */
    @Schema(name = "userType", description = "用户类型（0管理员，1普通用户）")
    private String userType;

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

}
