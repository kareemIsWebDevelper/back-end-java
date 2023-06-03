package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.EducationDTO;
import com.bfcai.topjob.model.Education;

import java.util.Set;

public interface EducationService {
    Set<Education> getAllEducation();

    Education getEducationById(Long educationId);

    Set<Education> getAllEducationForUser(Long educationId);

    Education saveOrUpdateEducation(Long educationId, EducationDTO educationDTO);

    Boolean deleteEducationById(Long educationId);

    void deleteAllEducationForUser(Long userId);
}
