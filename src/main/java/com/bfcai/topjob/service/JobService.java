package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.JobDTO;
import com.bfcai.topjob.dto.UserJobsPackage;
import com.bfcai.topjob.model.User;

import java.util.Set;

public interface JobService {
    Boolean saveJob(JobDTO jobDTO);

    Boolean deleteJobById(Long jobId);

    Set<User> getJobApplicants(Long jobId);

    Boolean userApplyJob(Long jobId, Long userId);

    Boolean isUserApplied(Long jobId, Long userId);

    UserJobsPackage fetchAllJobsForUser(Long userId);
}
