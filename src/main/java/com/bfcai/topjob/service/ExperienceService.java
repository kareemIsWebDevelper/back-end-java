package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.ExperienceDTO;
import com.bfcai.topjob.model.Experience;

import java.util.Set;

public interface ExperienceService {
    Set<Experience> getAllExperience();

    Experience getExperienceById(Long experienceId);

    Set<Experience> getAllExperienceForUser(Long userId);

    Experience saveOrUpdateExperience(Long experienceID, ExperienceDTO experienceDTO);

    Boolean deleteExperienceById(Long experienceId);

    void deleteAllExperienceForUser(Long userId);
}
