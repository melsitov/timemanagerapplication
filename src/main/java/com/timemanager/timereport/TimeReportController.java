package com.timemanager.timereport;

import com.timemanager.project.ProjectService;
import com.timemanager.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timereports")
public class TimeReportController {

    private TimeReportService timeReportService;
    private UserService userService;
    private ProjectService projectService;

    @Autowired
    public TimeReportController(TimeReportService timeReportService, UserService userService, ProjectService projectService) {
        this.timeReportService = timeReportService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping("/userid{uid}&projectid{pid}")
    public ResponseEntity<Long> addTimeReport(@PathVariable("uid") Long userid, @PathVariable("pid") Long projectid, @RequestBody TimeReportDTO timeReportDTO) {
        TimeReport timeReport = new TimeReport();
        timeReport.setHours(timeReportDTO.getHours());
        timeReport.setUser(userService.getById(userid));
        timeReport.setProject(projectService.getById(projectid));
        timeReport.setIncome(timeReport.getHours().multiply(projectService.getById(projectid).getRate()));
        timeReportService.createTimeReport(timeReport);
        return new ResponseEntity<>(timeReport.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/userid{uid}")
    public List<TimeReport> getTimeReportByUserId(@PathVariable("uid") Long id) {
        return timeReportService.findAllByUserId(id);
    }

    @GetMapping("/projectname&{name}")
    public List<TimeReport> getTimeReportByProjectName(@PathVariable("name") String name) {
        return timeReportService.findAllByProjectName(name);
    }

    @GetMapping("/reportperuserid{uid}")
    public ResponseEntity<TimeReportTotalDTO> reportTotal(@PathVariable("uid") Long userid, TimeReportTotalDTO timeReportTotalDTO) {
        timeReportTotalDTO.setUserId(userid);
        timeReportTotalDTO.setTotalIncome(timeReportService.reportTotal(userid));
        return new ResponseEntity<>(timeReportTotalDTO, HttpStatus.OK);
    }
}
