package com.timemanager.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {


    private UserRepository repository = Mockito.mock(UserRepository.class);
    private UserValidationService userValidationService = Mockito.mock(UserValidationService.class);
    private UserService victim;

    @BeforeEach
    void initUseCase() {
        victim = new UserService(repository, userValidationService);
    }

    @Test
    public void shouldInsertNewUser() {
        User user = user();
        when(repository.save(user)).thenReturn(user());
        Long result = victim.insertUser(user);
        assertEquals(user().getId(), result);
    }

    @Test
    public void shouldCallRepository() {
        victim.findAll();
        verify(repository).findAll();
    }

    @Test
    public void shouldFindById() {
        User user = user();
        when(repository.findById(1000L)).thenReturn(Optional.of(user));
        User result = victim.getById(1000L);
        assertEquals(user, result);
    }

    private User user() {
        User user = new User();
        user.setId(1000L);
        user.setLogin("TEST_LOGIN");
        user.setFirstName("TEST_FIRST_NAME");
        user.setSecondName("TEST_SECOND_NAME");
        return user;
    }

    private User updatedUser() {
        User updatedUser = new User();
        updatedUser.setId(1000L);
        updatedUser.setLogin("TEST_LOGIN");
        updatedUser.setFirstName("TEST_FIRST_NAME_AFTER_UPDATE");
        updatedUser.setSecondName("TEST_SECOND_NAME_AFTER_UPDATE");
        return updatedUser;
    }
}