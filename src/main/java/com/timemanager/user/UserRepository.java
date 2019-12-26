package com.timemanager.user;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
@Profile("hibernatedb")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
