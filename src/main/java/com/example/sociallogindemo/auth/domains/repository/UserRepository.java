package com.example.sociallogindemo.auth.domains.repository;

import com.example.sociallogindemo.auth.infrastructures.database.doma.dao.UserDao;
import com.example.sociallogindemo.auth.infrastructures.database.doma.entity.User;

public interface UserRepository {

    User getUser(String name);

    User getUser(String name, String password);

}
