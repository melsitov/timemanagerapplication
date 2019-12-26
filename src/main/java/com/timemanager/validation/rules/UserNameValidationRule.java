package com.timemanager.validation.rules;

import com.timemanager.user.User;
import com.timemanager.validation.exception.UserValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserNameValidationRule implements UserValidation {

    @Override
    public void validate(User user) {
        checkNotNull(user);
        if ((user.getFirstName() == null) || (user.getFirstName() == "")) {
            throw new UserValidationException("First name is mandatory");
        }
    }
}
