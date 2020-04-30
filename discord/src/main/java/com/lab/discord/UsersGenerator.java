package com.lab.discord;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class UsersGenerator {

    Random random = new Random();

    static final File EXAMPLE_NAME = new File("ExampleName.csv");
    static final File USERS = new File("Users.csv");

    static final String PHONE_NUMBER = "+380";
    static final int MAX_AGE = 100;

    static final String[] EXAMPLE_EMAIL = {"@gmail.com", "@ukr.net"};
    static final String[] GRAPHIC_FORMATS = {".png", ".jpg", ".gif"};

    List<String> usernames;

    {
        try {
            usernames = Files.readAllLines(EXAMPLE_NAME.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateUsername() {
        return usernames.get(random.nextInt(usernames.size()));
    }

    public String generateEmail() {
        String email = "";
        email = usernames.get(random.nextInt(usernames.size()));
        email = email + EXAMPLE_EMAIL[random.nextInt(EXAMPLE_EMAIL.length)];
        return email;
    }

    public String generatePhone(){
        String phoneNumber = "";
        phoneNumber= phoneNumber + PHONE_NUMBER;
        for(int i = 0; i<9; i++){
            phoneNumber = phoneNumber + random.nextInt(9);
        }
        return phoneNumber;
    }

    public int generateAge() {
        return random.nextInt(MAX_AGE);
    }

    public String generatePhoto() {
        String photo = "";
        photo = usernames.get(random.nextInt(usernames.size()));
        photo = photo + GRAPHIC_FORMATS[random.nextInt(GRAPHIC_FORMATS.length)];
        return photo;
    }

    public void generateUsers(){
        try {
            PrintWriter writer = new PrintWriter(USERS, "UTF-8");
            for(int i = 0;i<=1000; i++){
                writer.println(generateUsername() + " " +  generateEmail() + " " +
                        generatePhone() + " " + generateAge() + " " + generatePhoto());
            }
            writer.close();
            System.out.println("Generated successfully");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}