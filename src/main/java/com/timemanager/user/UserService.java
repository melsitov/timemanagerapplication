package com.timemanager.user;

import com.timemanager.validation.exception.UserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserValidationService userValidationService;

    @Autowired
    public UserService(UserRepository userRepository, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.userValidationService = userValidationService;
    }

    public Long insertUser(User user) {
        userValidationService.validateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else throw new UserValidationException("No user found under ID = " + id);
    }

    public Optional<User> deleteUserById(Long id) {
        userRepository.deleteById(id);
        return Optional.empty();
    }

    public User getByLogin(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            return userRepository.findByLogin(login).get();
        } else throw new UserValidationException("No user found with login = " + login);
    }

    public User updateUser(User user) {
        userValidationService.validateUser(user);
        userRepository.save(user);
        return user;
    }
}
