package com.hotel.dao.api;

import com.hotel.dao.model.User;

public interface UsrDao extends BaseDao<User> {
    User loadUserByUsername(String s);
}