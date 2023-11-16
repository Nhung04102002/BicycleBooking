package com.bicyclebooking.repository;

import com.bicyclebooking.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneAndPassword(String phone, String password);
    Optional<User> findByPhone(String phone);

    @Modifying
    @Transactional
    @Query("update User u set u.password = ?1 where u.id = ?2")
    void changePass(String pass, int userID);
}
