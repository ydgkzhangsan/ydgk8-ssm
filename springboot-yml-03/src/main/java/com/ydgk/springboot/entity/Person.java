package com.ydgk.springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-15 10:00
 */
// @ConfigurationProperties 用来将配置文件中的值注入到java类中
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String name;
    private String username;
    private Integer age;
    private Map<String,Object> pet;
    private List<String> animal;
    private List<String> interests;
    private List<Object> friends;
    private List<Map<String,Object>> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, Object> getPet() {
        return pet;
    }

    public void setPet(Map<String, Object> pet) {
        this.pet = pet;
    }

    public List<String> getAnimal() {
        return animal;
    }

    public void setAnimal(List<String> animal) {
        this.animal = animal;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<Object> getFriends() {
        return friends;
    }

    public void setFriends(List<Object> friends) {
        this.friends = friends;
    }

    public List<Map<String, Object>> getChilds() {
        return childs;
    }

    public void setChilds(List<Map<String, Object>> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                ", animal=" + animal +
                ", interests=" + interests +
                ", friends=" + friends +
                ", childs=" + childs +
                '}';
    }
}
