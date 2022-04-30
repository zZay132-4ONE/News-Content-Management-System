package com.github.zzay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zzay
 * @className User
 * @description User实体类
 * @create 2022/04/30 13:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
@Schema(name = "User", description = "用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = -6690897723869303541L;

    /**
     * ID (PK)
     */
    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "用户唯一标识符")
    private Long id;

    /**
     * Username
     */
    @Schema(name = "username", description = "用户名")
    private String username;

    /**
     * Password
     */
    @Schema(name = "password", description = "密码")
    private String password;

    /**
     * Account status (normal: 0 / abandoned: 1)
     */
    @Schema(name = "status", description = "账号状态（0正常 1停用）")
    private String status;

    /**
     * Email address
     */
    @Schema(name = "email", description = "邮箱")
    private String email;

    /**
     * Phone number
     */
    @Schema(name = "phone", description = "手机号")
    private String phone;

    /**
     * Gender (Male: 0 / Female: 1 / Unknown: 2)
     */
    @Schema(name = "sex", description = "用户性别（0男，1女，2未知）")
    private String sex;

    /**
     * Avatar
     */
    @Schema(name = "avatar", description = "头像")
    private String avatar;

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

    /**
     * Constructor
     *
     * @param id       ID
     * @param username Username
     * @param password Password
     */
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
