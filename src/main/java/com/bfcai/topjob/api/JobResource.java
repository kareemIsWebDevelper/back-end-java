package com.bfcai.topjob.api;

import com.bfcai.topjob.dto.JobDTO;
import com.bfcai.topjob.dto.UserJobDTO;
import com.bfcai.topjob.dto.UserJobsPackage;
import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/jobs")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class JobResource {

    private final JobService jobService;


    @PostMapping
    public ResponseEntity<Boolean> saveJob(@RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok(this.jobService.saveJob(jobDTO));
    }

    @DeleteMapping(path = "/{jobId}")
    public ResponseEntity<Boolean> deleteJobById(@PathVariable(value = "jobId") Long jobId) {
        return ResponseEntity.ok(this.jobService.deleteJobById(jobId));
    }


    @GetMapping(path = "/{jobId}/applicants")
    public ResponseEntity<Set<User>> getJobApplicants(@PathVariable(value = "jobId") Long jobId) {
        return ResponseEntity.ok(this.jobService.getJobApplicants(jobId));
    }

    @GetMapping(path = "/{jobId}/{userId}")
    public ResponseEntity<Boolean> userApplyJob(@PathVariable(value = "jobId") Long jobId, @PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.jobService.userApplyJob(jobId, userId));
    }

    @GetMapping(path = "/{jobId}/isApplied/{userId}")
    public ResponseEntity<Boolean> isUserApplied(@PathVariable(value = "jobId") Long jobId, @PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.jobService.isUserApplied(jobId, userId));
    }

    @GetMapping(path = "/{userId}/all-jobs")
    public ResponseEntity<UserJobsPackage> fetchAllJobsForUser(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(this.jobService.fetchAllJobsForUser(userId));
    }
}