package com.college.sri.eshwar.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {


    @Id
    int id;
    String name;
    String email;

    public User(){}

    public User(int id,String name,String email)
    {
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public int getId()
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

    public String toString()
    {
        return "id : "+id+" name : "+name+" email : "+email;
    }

}
