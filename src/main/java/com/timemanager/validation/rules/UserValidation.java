package com.timemanager.validation.rules;

import com.timemanager.user.User;
import com.timemanager.validation.exception.UserValidationException;

public interface UserValidation {

    void validate(User user);

    default void checkNotNull(User user) {
        if (user == null) {
            throw new UserValidationException("User cannot be null.");
        }
    }

}
