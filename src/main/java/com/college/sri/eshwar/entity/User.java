package com.college.sri.eshwar.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {


    @Id
    String id;
    String name;
    String email;

    String password;

    public User(){}

    public User(String id,String name,String email,String password)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String toString()
    {
        return "id : "+id+" name : "+name+" email : "+email;
    }

}
