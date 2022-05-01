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
     * Instantiate RedisTemplate and return it
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
        // Use "StringRedisSerializer" to serialize and deserialize the keys of redis
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Use "StringRedisSerializer" to serialize and deserialize the hash-keys of redis
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

}
