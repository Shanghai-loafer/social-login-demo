package com.example.social.login.demo.auth.infrastructures.database.repository;

import com.example.social.login.demo.auth.infrastructures.database.doma.dao.UserDao;
import com.example.social.login.demo.auth.infrastructures.database.doma.entity.User;
import com.example.social.login.demo.auth.domains.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    @Override
    public User getUser(String name) {
        return userDao.selectByName(name);
    }

    @Override
    public User getUser(String name, String password) {
        return userDao.selectByCredential(name, password);
    }
}
