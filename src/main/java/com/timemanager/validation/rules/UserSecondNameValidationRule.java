package com.timemanager.validation.rules;

import com.timemanager.user.User;
import com.timemanager.validation.exception.UserValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserSecondNameValidationRule implements UserValidation {

    @Override
    public void validate(User user) {
        checkNotNull(user);
        if ((user.getSecondName() == null) || (user.getSecondName() == "")) {
            throw new UserValidationException("Second name is mandatory");
        }
    }
}
