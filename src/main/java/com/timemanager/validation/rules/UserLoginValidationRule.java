package com.timemanager.validation.rules;

import com.timemanager.user.User;
import com.timemanager.user.UserRepository;
import com.timemanager.validation.exception.UserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginValidationRule implements UserValidation {
    private static final int MIN_LOGIN_LENGTH = 3;
    private static final int MAX_LOGIN_LENGTH = 32;

    private UserRepository userRepository;

    @Autowired
    public UserLoginValidationRule(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User user) {
        checkNotNull(user);
        if (user.getLogin() == null) {
            throw new UserValidationException("Login cannot be null.");
        }
        if ((user.getLogin().length() < MIN_LOGIN_LENGTH) || (user.getLogin().length() > MAX_LOGIN_LENGTH)) {
            throw new UserValidationException("Invalid login length. It should be from 3 to 32 letters long. Try again.");
        }
        if (userRepository.findByLogin(user.getLogin()).equals(user.getLogin())) {
            throw new UserValidationException("User with this login already exists");
        }
    }
}
