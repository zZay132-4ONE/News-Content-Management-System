package com.github.zzay.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zzay
 * @className RedisConfig
 * @description Redis配置
 * @create 2022/04/21 16:17
 */
@Configuration
public class RedisConfig {

    /**
     * 实例化redisTemplate并返回
     *
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // Redis Template
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // FastJson Redis Serializer
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        template.setConnectionFactory(connectionFactory);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

}
