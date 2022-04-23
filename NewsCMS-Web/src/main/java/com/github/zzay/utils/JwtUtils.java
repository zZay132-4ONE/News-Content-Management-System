package com.github.zzay.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author zzay
 * @className JwtUtils
 * @description JWT工具类
 * @create 2022/04/21 16:29
 */
public class JwtUtils {

    /**
     * JWT有效时长
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;  // 60 * 60 *1000  一个小时

    /**
     * 秘钥明文
     */
    public static final String JWT_KEY = "zzay";

    /**
     * 解析JWT
     *
     * @param jwt JWT
     * @return Claims
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generateKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 构建JwtBuilder，并且利用它生成JWT并返回
     *
     * @param subject 主题，可以是JSON数据
     * @return 生成的JWT
     */
    public static String createJWT(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 构建JwtBuilder，并且利用它生成JWT并返回
     *
     * @param subject   主题，可以是JSON数据
     * @param ttlMillis 过期时长
     * @return 生成的JWT
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 构建JwtBuilder，并且利用它生成JWT并返回
     *
     * @param subject   主题，可以是JSON数据
     * @param ttlMillis 过期时长
     * @param id        ID
     * @return 生成的JWT
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, id);
        return jwtBuilder.compact();
    }

    /**
     * 配置JwtBuilder实例并返回
     *
     * @param subject   主题，可以是JSON数据
     * @param ttlMillis 过期时长
     * @param uuid      随机生成的UUID
     * @return JwtBuilder实例
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        // 记录时间
        long nowMillis = System.currentTimeMillis();
        if (ttlMillis == null) ttlMillis = JwtUtils.JWT_TTL;
        long expireMillis = nowMillis + ttlMillis;
        // 记录日期
        Date nowDate = new Date(nowMillis);
        Date expireDate = new Date(expireMillis);
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成秘钥
        SecretKey secretKey = generateKey();
        // 利用Jwts配置JwtBuilder实例并返回
        return Jwts.builder()
                .setId(uuid)                // 唯一的ID
                .setSubject(subject)        // 主题，可以是JSON数据
                .setIssuer("zzay")          // 签发者
                .setIssuedAt(nowDate)       // 签发时间
                .setExpiration(expireDate)  // 过期时间
                .signWith(signatureAlgorithm, secretKey);  // 使用HS256对称加密算法签名，并附上秘钥
    }

    /**
     * 生成加密后的秘钥
     *
     * @return 加密后的秘钥
     */
    private static SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    /**
     * 将UUID随机生成的字符串作为生成token返回
     *
     * @return 利用UUID随机生成的token字符串
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {

    }

}
