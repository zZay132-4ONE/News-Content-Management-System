package com.github.zzay.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zzay
 * @className UserDto
 * @description User DTO
 * @create 2022/04/30 23:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = -67213226995165421L;

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

}
