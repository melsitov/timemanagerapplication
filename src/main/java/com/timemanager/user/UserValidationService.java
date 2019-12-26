package com.timemanager.user;

import com.timemanager.validation.rules.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserValidationService {
    private Set<UserValidation> validationRules;

    @Autowired
    public UserValidationService(Set<UserValidation> validationRules) {
        this.validationRules = validationRules;
    }

    public void validateUser(User user) {
        validationRules.forEach(s -> s.validate(user));
    }
}
