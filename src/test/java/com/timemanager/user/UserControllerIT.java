package com.timemanager.user;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    scripts = "/delete-test-user.sql")
    })
    public void shouldInsertUser() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson()))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindUserById() throws Exception {
        mockMvc.perform(get("/users/id1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(1000));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    scripts = "/create-test-user.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    scripts = "/delete-test-user.sql")
    })
    public void shouldFindUserByLogin() throws Exception {
        mockMvc.perform(get("/users/login&TEST_LOGIN")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson()))
                .andExpect(status().isOk());
    }

    private String userJson() throws JSONException {
        return new JSONObject()
                .put("login", "TEST_LOGIN")
                .put("firstName", "TEST_FIRSTNAME")
                .put("secondName", "TEST_SECONDNAME")
                .toString();
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO(1000L, "TEST_LOGIN", "TEST_FIRST_NAME", "TEST_SECOND_NAME");
        userDTO.setId(1000L);
        userDTO.setLogin("TEST_LOGIN");
        userDTO.setFirstName("TEST_FIRST_NAME");
        userDTO.setSecondName("TEST_SECOND_NAME");
        return userDTO;
    }

    private User user() {
        User user = new User();
        user.setId(1000L);
        user.setLogin("TEST_LOGIN");
        user.setFirstName("TEST_FIRST_NAME");
        user.setSecondName("TEST_SECOND_NAME");
        return user;
    }
}