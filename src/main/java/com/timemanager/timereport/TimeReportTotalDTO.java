package com.timemanager.timereport;

import java.math.BigDecimal;

public class TimeReportTotalDTO {
    private Long userId;
    private BigDecimal totalIncome;

    public TimeReportTotalDTO(Long userId, BigDecimal totalIncome){
        this.userId = userId;
        this.totalIncome = totalIncome;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "TimeReportTotalDTO{" +
                "userId=" + userId +
                ", totalIncome=" + totalIncome +
                '}';
    }
}
