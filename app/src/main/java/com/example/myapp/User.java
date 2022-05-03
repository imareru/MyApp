package com.example.myapp;

public class User {

    private long id;
    private String name;
    private int age;
    private String city;

    User(long id, String name, int age, String city ){
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.city;
    }
}