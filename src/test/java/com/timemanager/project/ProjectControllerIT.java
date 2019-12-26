package com.timemanager.project;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    scripts = "/delete-test-project.sql")
    })
    public void shouldCreateProject() throws Exception {
        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFindById() throws Exception {
        mockMvc.perform(get("/projects/id3000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(3000));
    }

    private Project project() {
        Project project = new Project();
        project.setId(3000L);
        project.setName("TEST_NAME");
        project.setRate(BigDecimal.TEN);
        return project;
    }

    private String projectJson() throws JSONException {
        return new JSONObject()
                .put("name", "TEST_NAME")
                .put("rate", BigDecimal.TEN)
                .toString();
    }
}