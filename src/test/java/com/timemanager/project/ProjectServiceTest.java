package com.timemanager.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class ProjectServiceTest {

    private ProjectRepository repository = Mockito.mock(ProjectRepository.class);
    private ProjectService victim;

    @BeforeEach
    void initUseCase() {
        victim = new ProjectService(repository);
    }

    @Test
    public void shouldCreateProject(){
        Project project = project();
        when(repository.save(project)).thenReturn(project());
        Long result = victim.createProject(project);
        assertEquals(project.getId(), result);
    }

    @Test
    public void shouldFindProjectById(){
        Project project = project();
        when(repository.findById(1000L)).thenReturn(Optional.of(project));
        Project result = victim.getById(1000L);
        assertEquals(project, result);
    }

    @Test
    public void shouldUpdateProject(){
        Project project = project();
        Project updatedProject = updatedProject();
        when(repository.save(project)).thenReturn(updatedProject);
        project.setName("TEST_NAME_UPDATE");
        project.setRate(BigDecimal.TEN);
        Project update = victim.updateProject(project);
        assertEquals(updatedProject, update);
    }

    private Project project() {
        Project project = new Project();
        project.setId(2000L);
        project.setName("TEST_NAME");
        project.setRate(BigDecimal.TEN);
        return project;
    }

    private Project updatedProject() {
        Project project = new Project();
        project.setId(2000L);
        project.setName("TEST_NAME_UPDATE");
        project.setRate(BigDecimal.TEN);
        return project;
    }
}