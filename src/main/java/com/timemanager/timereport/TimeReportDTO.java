package com.timemanager.timereport;

import java.math.BigDecimal;

public class TimeReportDTO {
    private Long id;
    private Long userId;
    private Long projectId;
    private BigDecimal hours;
    private BigDecimal income;

    public TimeReportDTO(Long id, Long userId, Long projectId, BigDecimal hours, BigDecimal income) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.hours = hours;
        this.income = income;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "TimeReportDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", hours=" + hours +
                ", income=" + income +
                '}';
    }
}
