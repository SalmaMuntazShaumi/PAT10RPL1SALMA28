package com.example.pat_10rpl1_28_salma;

import java.io.Serializable;

public class PhoneModel implements Serializable {
    private String username,password,name,phone,gender;
    private int age;

    public PhoneModel(){

    }

    public PhoneModel(String name, String username, String phone, String gender, String password, int age) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.age = age;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
