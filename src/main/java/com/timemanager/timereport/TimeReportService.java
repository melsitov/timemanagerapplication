package com.timemanager.timereport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TimeReportService {
    private TimeReportRepository repository;

    @Autowired
    public TimeReportService(TimeReportRepository repository) {
        this.repository = repository;
    }

    public Long createTimeReport(TimeReport timeReport) {
        repository.save(timeReport);
        return timeReport.getId();
    }

    public List<TimeReport> findAllByUserId(Long id) {
        return repository.findAllByUserId(id);
    }

    public List<TimeReport> findAllByProjectName(String name) {
        return repository.findAllByProjectName(name);
    }

    public BigDecimal reportTotal(Long userId) {
        return repository.sumPerUserPerProject(userId);
    }

}
