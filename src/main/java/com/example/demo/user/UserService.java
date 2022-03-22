package com.example.demo.user;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addNewUser(UserTable user){
        Optional<UserTable> userByName = userRepository.findUserByUserName(user.getUserName());

        if(user.getUserName().isEmpty() || user.getPassword().isEmpty()){
            throw new IllegalStateException("Please provide with valid data.");
        }

        if(userByName.isPresent()){
            throw new IllegalStateException("This user name is already taken.");
        }
        userRepository.save(user);
        System.out.println(user);
    }

    public boolean logIn(String userName, String password){
        Optional<UserTable> userByName = userRepository.findUserByUserName(userName);
        if(!userByName.isPresent()){
            throw new IllegalStateException("This user does not exist.");
        }
        if(!(password.compareTo(userByName.map
                (UserTable::getPassword).orElse(null)) == 0)){

            throw new IllegalStateException("User password doesn't match!");

        }
        return userByName.map
                (UserTable::isPatient).orElse(null);
    }

    @Transactional
    public boolean logIn(UserTable user){
        Optional<UserTable> userByName = userRepository.findUserByUserName(user.getUserName());

        if(!userByName.isPresent()){
            throw new IllegalStateException("This user does not exist.");
        }
        if(!(user.getUserName().compareTo(userByName.map
                (UserTable::getPassword).orElse(null)) == 0)){

            throw new IllegalStateException("User password doesn't match!");

        }
        UserTable currentUser = userByName.get();

        userRepository.setAllInactive();
        userRepository.setActive(currentUser.getUserID());
        return currentUser.isPatient();
    }

    public void setInactive(){
        userRepository.setAllInactive();
    }
    public void setActive(UserTable user){
        userRepository.setActive(user.getUserID());
    }
    public Optional<UserTable> getActiveUser(){
        return userRepository.findActiveUser();
    }
}
