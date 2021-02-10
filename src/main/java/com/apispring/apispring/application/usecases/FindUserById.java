package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.UserRepository;
import com.apispring.apispring.domain.exception.UserNotFound;
import com.apispring.apispring.domain.model.UserModel;

public class FindUserById {

    private UserRepository userRepository;

    public FindUserById(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel handle(int id){
       UserModel userModel = this.userRepository.findById(id);

       if (userModel==null){
           throw new UserNotFound();
       }
       return userModel;
    }
}
