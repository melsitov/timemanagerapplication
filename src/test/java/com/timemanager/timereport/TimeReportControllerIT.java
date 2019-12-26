package com.timemanager.timereport;

import com.timemanager.project.Project;
import com.timemanager.user.User;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeReportControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    scripts = "/delete_test_timereport.sql")
    })
    public void shouldCreateTimeReport() throws Exception {
        mockMvc.perform(post("/timereports/userid1000&projectid3000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(timeReportJson()))
                .andExpect(status().isCreated());
    }

    private String timeReportJson() throws JSONException {
        return new JSONObject()
                .put("hours", 10)
                .toString();
    }

    private TimeReport timeReport() {
        TimeReport timeReport = new TimeReport();
        timeReport.setId(2000L);
        timeReport.setHours(BigDecimal.ONE);
        timeReport.setProject(project());
        timeReport.setUser(user());
        timeReport.setIncome(timeReport.getHours().multiply(project().getRate()));
        return timeReport;
    }

    private Project project() {
        Project project = new Project();
        project.setId(3000L);
        project.setName("TEST_NAME");
        project.setRate(BigDecimal.TEN);
        return project;
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