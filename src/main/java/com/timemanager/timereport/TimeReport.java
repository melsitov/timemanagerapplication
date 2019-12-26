package com.timemanager.timereport;

import com.timemanager.project.Project;
import com.timemanager.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "timereport", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})

public class TimeReport {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hours")
    private BigDecimal hours;
    @Column(name = "income")
    private BigDecimal income;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
