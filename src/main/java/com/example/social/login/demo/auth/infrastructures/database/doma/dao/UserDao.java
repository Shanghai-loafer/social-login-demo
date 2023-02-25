package com.example.social.login.demo.auth.infrastructures.database.doma.dao;

import com.example.social.login.demo.auth.infrastructures.database.doma.entity.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface UserDao {
    @Select
    User selectById(Integer id);

    @Select
    User selectByName(String name);

    @Select
    User selectByCredential(String name, String password);
}
