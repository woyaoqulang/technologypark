package com.clare.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisPoolFactory {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisPool getJedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        config.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        config.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
        JedisPool pool = new JedisPool(config, redisProperties.getHost(), redisProperties.getPort(), 100,
                redisProperties.getPassword(), redisProperties.getDatabase(), "user");

        return pool;
    }
}
