package com.lab.discord.repos;

import com.lab.discord.rest.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findById(int id);

    List<User> findByUsername(String username);


    @Transactional
    @Modifying
    @Query("update User  set  username = ?2, email = ?3, phone = ?4, age = ?5, photo = ?6 where id = ?1")
    void updateUser(int id, String username, String email, String phone, int age, String photo);

    @Transactional
    @Modifying
    @Query("delete from User a where a.id = ?1")
    void deleteById(int id);;

    @Transactional
    @Modifying
    void deleteByUsername(String username);
}