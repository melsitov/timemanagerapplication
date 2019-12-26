package com.timemanager.validation.rules;

import com.timemanager.user.User;
import com.timemanager.user.UserRepository;
import com.timemanager.validation.exception.UserValidationException;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class UserLoginValidationRuleTest {

    private UserRepository repository = Mockito.mock(UserRepository.class);
    private UserLoginValidationRule victim;

    @BeforeEach
    void initUseCase() {
        victim = new UserLoginValidationRule(repository);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private User input;
    private User inputDuplicate;

    @Test
    public void shouldValidateUser() {
        input = user("TESTLOGIN");
        victim.validate(input);
    }

    @Test
    public void shouldThrowExceptionLoginNull() {
        input = user(null);
        assertThrows(UserValidationException.class, () ->
                victim.validate(input), "Login cannot be null.");
    }

    @Test
    public void shouldThrowExceptionLoginShort() {
        input = user("TE");
        assertThrows(UserValidationException.class, () ->
                victim.validate(input), "Invalid login length. It should be from 3 to 32 letters long. Try again.");
    }

    @Test
    public void shouldThrowExceptionLoginLong() {
        input = user("TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        assertThrows(UserValidationException.class, () ->
                victim.validate(input), "Invalid login length. It should be from 3 to 32 letters long. Try again.");
    }

    private User user(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }
}