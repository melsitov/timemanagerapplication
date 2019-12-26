package com.timemanager.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Long createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setRate(projectDTO.getRate());
        projectService.createProject(project);
        return project.getId();
    }

    @GetMapping("/id{id}")
    public Project getById(@PathVariable("id") Long id) {
        return projectService.getById(id);
    }

    @PatchMapping("id{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody ProjectDTO projectDTO) {
        Project project = projectService.getById(id);
        project.setName(projectDTO.getName());
        project.setRate(projectDTO.getRate());
        Project updatedProject = projectService.updateProject(project);
        return new ResponseEntity<>(updatedProject, HttpStatus.ACCEPTED);
    }
}
