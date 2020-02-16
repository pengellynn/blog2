package com.ripon.adminserver.service;

import com.ripon.adminserver.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    User getUserByUsername(String username);
    List<User> getAllUser();
    Integer deleteUser(Integer id);
}
