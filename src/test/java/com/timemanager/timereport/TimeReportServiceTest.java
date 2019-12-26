package com.timemanager.timereport;

import com.timemanager.project.Project;
import com.timemanager.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TimeReportServiceTest {

    private TimeReportRepository repository = Mockito.mock(TimeReportRepository.class);
    private TimeReportService victim;

    @BeforeEach
    void initUseCase() {
        victim = new TimeReportService(repository);
    }

    @Test
    public void shouldCreateReport(){
        TimeReport timeReport = timeReport();
        when(repository.save(timeReport)).thenReturn(timeReport());
        Long result = victim.createTimeReport(timeReport);
        assertEquals(timeReport.getId(), result);
    }

    @Test
    public void shouldReturnSum(){
        TimeReport timeReport = timeReport();
        when(repository.sumPerUserPerProject(user().getId())).thenReturn(timeReport().getIncome());
        BigDecimal result = victim.reportTotal(1000L);
        assertEquals(timeReport.getIncome(), result);
    }

    private TimeReport timeReport(){
        TimeReport timeReport = new TimeReport();
        timeReport.setId(3000L);
        timeReport.setHours(BigDecimal.ONE);
        timeReport.setProject(project());
        timeReport.setUser(user());
        timeReport.setIncome(timeReport.getHours().multiply(project().getRate()));
        return timeReport;
    }

    private Project project() {
        Project project = new Project();
        project.setId(2000L);
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