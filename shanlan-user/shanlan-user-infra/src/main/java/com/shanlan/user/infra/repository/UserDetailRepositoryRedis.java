package com.shanlan.user.infra.repository;

import com.alibaba.fastjson.JSONObject;
import com.shanlan.opf.core.viewobjects.NodeJsSession;
import com.shanlan.user.core.domain.UserDetail;
import com.shanlan.user.core.repository.UserDetailRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.TimeUnit;

/**
 * Created by albertliu on 14/10/28.
 */
@Named
public class UserDetailRepositoryRedis implements UserDetailRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailRepositoryRedis.class);
    @Inject
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getFromCache(String key) {
        logger.info(key);
        String value = stringRedisTemplate.boundValueOps(key).get();
        logger.info(value);
        return value;
    }

    @Override
    public void saveInCache(String key, String value, Integer expireInMinutes) {
        stringRedisTemplate.opsForValue().set(key, value, expireInMinutes, TimeUnit.MINUTES);
    }

    @Override
    public void update(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
}
