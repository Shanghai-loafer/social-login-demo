package com.example.social.login.demo.auth.domains.repository;

import com.example.social.login.demo.auth.infrastructures.database.doma.entity.User;

public interface UserRepository {

    User getUser(String name);

    User getUser(String name, String password);

}
