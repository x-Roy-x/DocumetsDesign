package com.lab.discord.rest.crud;

import com.lab.discord.rest.entity.User;
import com.lab.discord.repos.UserRepository;
import com.lab.discord.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    UsersService usersService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                       String name, Map<String,Object> model) {

        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public  String main (Map<String, Object>model){
        Iterable<User> all = usersService.findAll();
        model.put("users",all);
        return "main";
    }

    @GetMapping("/csv")
    public  String dataFromCSV (Map<String, Object>model){
        usersService.saveDataFromCsv();
        Iterable<User> all = usersService.findAll();
        model.put("users",all);
        return "main";
    }

    @PostMapping("getUser")
    public String filter(@RequestParam String username ,Map<String, Object>model){
        Iterable<User> byUsermane;
        if (username != null && !username.isEmpty()){
            byUsermane = usersService.findByUsername(username);
        }else {
            byUsermane = usersService.findAll();
        }
        model.put("users",byUsermane);
        return "main";
    }

    @PostMapping("createUser")
    public String add(@RequestParam String username, @RequestParam String email, @RequestParam String phone,
                      @RequestParam int age, @RequestParam String photo, Map<String, Object>model){

        User user = new User(username, email, phone,age,photo);
        usersService.createUser(user);

        Iterable<User> users = usersService.findAll();
        model.put("users",users);
        return "main";
    }

    @PostMapping("updateUser")
    public String update(@RequestParam int id,@RequestParam String username, @RequestParam String email, @RequestParam String phone,
                      @RequestParam int age, @RequestParam String photo, Map<String, Object>model){

        usersService.updateUser(id,username, email, phone,age,photo);

        Iterable<User> usersByID = usersService.findByUserId(id);
        model.put("updateUsers",usersByID);

        Iterable<User> users = usersService.findAll();
        model.put("users",users);
        return "main";
    }

    @PostMapping("deleteUser")
    public String delete(@RequestParam String username ,Map<String, Object>model){
        if (username != null && !username.isEmpty()){
            usersService.deleteByUsername(username);
        }else {
            usersService.deleteAllUsers();
        }
        Iterable<User> users = usersService.findAll();
        model.put("users",users);
        return "main";
    }
}