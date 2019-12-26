package com.timemanager.timereport;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
@Profile("hibernatedb")
public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {

    List<TimeReport> findAllByUserId(Long userId);

    List<TimeReport> findAllByProjectName(String name);

    @Query(value = "select sum(p.rate * t.hours) from timereport t " +
            "inner join project p on t.project_id = p.id " +
            "inner join user u on t.user_id = u.id " +
            "where u.id = ?1 " , nativeQuery = true)
    BigDecimal sumPerUserPerProject(Long userId);
}
