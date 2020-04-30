package com.lab.discord;

import com.lab.discord.service.UsersService;

public class ControllerGenerator {
    public static void main(String[] args) {
        UsersGenerator generatorCSV = new UsersGenerator();
        generatorCSV.generateUsers();
    }
}