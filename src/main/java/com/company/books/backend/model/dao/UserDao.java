package com.company.books.backend.model.dao;

import com.company.books.backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
    @Query("select u from User u where u.username=?1")
    User findByUsernameV2(String username);
}
