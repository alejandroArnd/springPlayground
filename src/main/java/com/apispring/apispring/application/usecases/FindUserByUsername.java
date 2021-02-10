package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.UserRepository;
import com.apispring.apispring.domain.exception.UserNotFound;
import com.apispring.apispring.domain.model.UserModel;

public class FindUserByUsername {

    private UserRepository userRepository;

    public FindUserByUsername(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel handle(String username){
        UserModel userModel = this.userRepository.findByUsername(username);

        if (userModel==null){
            throw new UserNotFound();
        }
        return userModel;
    }
}
