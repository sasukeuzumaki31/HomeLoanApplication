package com.group4.demo.service;

import com.group4.demo.entity.User;

public interface IUserService {

    public User addNewUser(User user);
    public User signIn(User user);
    public User signOut(User user);
}
