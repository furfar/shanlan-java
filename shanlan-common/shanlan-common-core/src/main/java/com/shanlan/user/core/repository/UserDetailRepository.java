package com.shanlan.user.core.repository;

import com.shanlan.user.core.domain.UserDetail;

/**
 * Created by albertliu on 14/10/28.
 */
public interface UserDetailRepository {

    public String getFromCache(String key);

    void saveInCache(String key, String value, Integer expireInMinutes);

    void update(String key, String value);
}
