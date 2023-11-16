package com.bicyclebooking.service;

import com.bicyclebooking.model.User;
import com.bicyclebooking.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User registerUser( String fullName, String email ,String phone, String password){
        if (phone == null && password == null) {
            return null;
        }
        else{
            if (userRepository.findByPhone(phone).isPresent()){
                System.out.println("Duplicate account");
                return null;
            }
            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            return userRepository.save(user);
        }
    }

    public User authenticate(String phone, String password){
        return userRepository.findByPhoneAndPassword(phone, password).orElse(null);
    }

    public User get(Integer id){
        Optional<User> result = userRepository.findById(id);
        return result.get();
    }

    public User get(String phone){
        Optional<User> result = userRepository.findByPhone(phone);
        return result.get();
    }

    public User save(User user){
        User user1 = get(user.getId());
        if (user.getPhone() == null) {
            return null;
        }
        else{
            if (userRepository.findByPhone(user.getPhone()).isPresent() && !user.getPhone().equals(user1.getPhone())){
                System.out.println("Duplicate account");
                return null;
            }
            return userRepository.save(user);
        }
    }

    public void changePassword(String pass, int userID){userRepository.changePass(pass, userID);}

    public void delete(Integer id){
        userRepository.deleteById(id);
    }
}

