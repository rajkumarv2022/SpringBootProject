package com.college.sri.eshwar.repository;

import com.college.sri.eshwar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByName(String name);

}