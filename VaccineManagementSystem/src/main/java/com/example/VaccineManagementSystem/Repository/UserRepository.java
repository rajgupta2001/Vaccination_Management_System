package com.example.VaccineManagementSystem.Repository;

import com.example.VaccineManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //Just define fun, and we can use it just like prebuilt fun
    User findByEmail(String email);
}
