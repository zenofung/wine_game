package com.wine.game.wine.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wine.game.wine.security.listen.ReidsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: #        ┏┓　　　┏┓+ +
 * #　　　┏┛┻━━━┛┻┓ + +
 * #　　　┃　　　　　　　┃
 * #　　　┃　　　━　　　┃ ++ + + +
 * #　　 ████━████ ┃+
 * #　　　┃　　　　　　　┃ +
 * #　　　┃　　　┻　　　┃
 * #　　　┃　　　　　　　┃ + +
 * #　　　┗━┓　　　┏━┛
 * #　　　　　┃　　　┃
 * #　　　　　┃　　　┃ + + + +
 * #　　　　　┃　　　┃　　　　Codes are far away from bugs with the animal protecting
 * #　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * #　　　　　┃　　　┃
 * #　　　　　┃　　　┃　　+
 * #　　　　　┃　 　　┗━━━┓ + +
 * #　　　　　┃ 　　　　　　　┣┓
 * #　　　　　┃ 　　　　　　　┏┛
 * #　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * #　　　　　　┃┫┫　┃┫┫
 * #　　　　　　┗┻┛　┗┻┛+ + + +
 * #
 * @author: zeno fung
 * @create: 2022-03-14 14:51
 */

@Configuration
@EnableCaching
public class MyRedisConfig extends CachingConfigurerSupport
{
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }


    @Bean
    public ReidsListener codeExpiredListener() {
        return new ReidsListener(this.redisMessageListenerContainer());
    }

}
