package com.timemanager.project;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Profile("hibernatedb")
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
