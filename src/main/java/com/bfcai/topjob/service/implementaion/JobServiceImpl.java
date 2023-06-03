package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.JobDTO;
import com.bfcai.topjob.dto.UserJobDTO;
import com.bfcai.topjob.dto.UserJobsPackage;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Company;
import com.bfcai.topjob.model.Job;
import com.bfcai.topjob.model.Skill;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.CompanyRepository;
import com.bfcai.topjob.repository.JobRepository;
import com.bfcai.topjob.repository.UserRepository;
import com.bfcai.topjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Override
    public Boolean saveJob(JobDTO jobDTO) {
        try {
            Company company = this.companyRepository.findById(jobDTO.getCompanyId()).orElseThrow(() -> new NotFoundException("No company exist with id : " + jobDTO.getCompanyId()));
            Job job = this.modelMapper.map(jobDTO, Job.class);
            job.setCompany(company);
            this.jobRepository.save(job);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean deleteJobById(Long jobId) {
        try {
            Job job = this.jobRepository.fetchJobWithApplicants(jobId).orElseThrow(() -> new NotFoundException("No job exist with id : " + jobId));
            job.getApplicants().forEach(user -> user.getJobs().remove(job));
            this.jobRepository.delete(job);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Set<User> getJobApplicants(Long jobId) {
        return this.jobRepository.fetchJobApplicants(jobId);
    }

    @Override
    @Transactional
    public Boolean userApplyJob(Long jobId, Long userId) {
        try {
            Job job = this.jobRepository.findById(jobId).orElseThrow(() -> new NotFoundException("No job exist with id : " + jobId));
            User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
            job.getApplicants().add(user);
            user.getJobs().add(job);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Boolean isUserApplied(Long jobId, Long userId) {
        Job job = this.jobRepository.findById(jobId).orElseThrow(() -> new NotFoundException("No job exist with id : " + jobId));
        for (User applicant : job.getApplicants()) {
            if (Objects.equals(applicant.getId(), userId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserJobsPackage fetchAllJobsForUser(Long userId) {
        Set<UserJobDTO> userJobDTOList = this.jobRepository.fetchAllJobsForUser(userId);
        UserJobsPackage userJobsPackage = new UserJobsPackage();
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("No user exist with id : " + userId));
        userJobDTOList.forEach(userJobDTO -> {
            if (userJobDTO.getTitle().equalsIgnoreCase(user.getJobTitle()) || this.isSkillsMatchingRequirements(userJobDTO.getRequirement(), user.getSkills())) {
                userJobsPackage.getMatching().add(userJobDTO);
            } else {
                userJobsPackage.getOthers().add(userJobDTO);
            }
        });
        return userJobsPackage;
    }

    private boolean isSkillsMatchingRequirements(String requirement, Set<Skill> skills) {
        final int[] matchingCount = {0};
        skills.forEach(skill -> {
            if (requirement.toLowerCase().contains(skill.getSkillName().toLowerCase())) {
                matchingCount[0]++;
            }
        });
        return matchingCount[0] >= 3;
    }
}
