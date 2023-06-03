package com.bfcai.topjob.repository;


import com.bfcai.topjob.dto.UserJobDTO;
import com.bfcai.topjob.model.Job;
import com.bfcai.topjob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    @Query(value = "select distinct j.applicants from Job j where j.id=:jobId")
    Set<User> fetchJobApplicants(@Param(value = "jobId") Long jobId);

    @Query(value = "select distinct j from Job j left join fetch j.applicants where j.id=:jobId")
    Optional<Job> fetchJobWithApplicants(@Param(value = "jobId") Long jobId);

    @Query(value = "select distinct" +
            " new com.bfcai.topjob.dto.UserJobDTO(j.id,j.title,j.location,j.employmentType,j.description,j.requirement,j.date,j.company.name)" +
            " from Job j")
    Set<UserJobDTO> fetchAllJobsForUser(@Param(value = "userId") Long userId);
}
