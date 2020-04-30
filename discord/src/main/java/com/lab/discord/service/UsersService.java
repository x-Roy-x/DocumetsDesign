package com.lab.discord.service;

import com.lab.discord.repos.UserRepository;
import com.lab.discord.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class UsersService {


    @Autowired
    private UserRepository userRepository;

     public Iterable<User> findAll(){
        return userRepository.findAll();
     }

    public User createUser(User user) {
        return userRepository.save(user);
    }

     public List<User> findByUserId(int id){
         return userRepository.findById(id);
     }

    public List<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

     public void deleteByIdUser(int id){
         userRepository.deleteById(id);
     }
     public void deleteByUsername(String username){
         userRepository.deleteByUsername(username);
     }
     public void deleteAllUsers() {
         userRepository.deleteAll();
     }

     public void saveDataFromCsv(){
        String line ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("Users.csv"));
            while ((line = br.readLine())!=null){
                String[] data = line.split(" ");
                User user = new User(data[0],data[1],data[2],Integer.parseInt(data[3]),data[4]);
                userRepository.save(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

    public void updateUser(int id, String username, String email, String phone,
                          int age, String photo) {
        userRepository.updateUser(id,username,email,phone,age,photo);
    }
}
