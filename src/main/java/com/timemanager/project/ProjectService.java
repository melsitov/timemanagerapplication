package com.timemanager.project;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Long createProject(Project project) {
        repository.save(project);
        return project.getId();
    }

    public Project getById(Long id) {
        return repository.findById(id).get();
    }

    public Project updateProject(Project project) {
        return repository.save(project);
    }
}
