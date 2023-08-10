package com.rapidesell.service;

import com.rapidesell.dao.UserDao;
import com.rapidesell.model.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User registerAdmin(User user) {
        return userDao.save(user);
    }

    public User resetPassword(String email, String phone, String password) {
        Optional<User> optionalUser=userDao.findByEmailidAndMobileno(email,phone);
        if(optionalUser.isPresent()){
            optionalUser.get().setPassword(password);
        }
        return optionalUser.get();
    }

    public User findByEmailidAndPassword(String emailId, String password) {
        Optional<User> optionalUser=userDao.findByEmailidAndPassword(emailId,password);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }
}
