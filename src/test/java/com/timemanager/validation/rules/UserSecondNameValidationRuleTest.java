package com.timemanager.validation.rules;

import com.timemanager.user.User;
import com.timemanager.validation.exception.UserValidationException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class UserSecondNameValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private UserSecondNameValidationRule victim = new UserSecondNameValidationRule();
    private User input;

    @Test
    public void shouldValidateUser(){
        input = user("TEST_NAME");
        victim.validate(input);
    }

    @Test
    public void shouldThrowException(){
        input = user("");
        assertThrows(UserValidationException.class, ()->
                        victim.validate(input),
                "Second name is mandatory");
    }

    private User user(String secondName){
        User user = new User();
        user.setSecondName(secondName);
        return user;
    }
}