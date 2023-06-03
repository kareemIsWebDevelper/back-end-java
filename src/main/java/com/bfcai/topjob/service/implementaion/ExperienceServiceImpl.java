package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.ExperienceDTO;
import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Education;
import com.bfcai.topjob.model.Experience;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.ExperienceRepository;
import com.bfcai.topjob.service.ExperienceService;
import com.bfcai.topjob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public ExperienceServiceImpl(ExperienceRepository experienceRepository, UserService userService, ModelMapper modelMapper) {
        this.experienceRepository = experienceRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Experience> getAllExperience() {
        return new HashSet<>(this.experienceRepository.findAll());
    }

    @Override
    public Experience getExperienceById(Long experienceId) {
        return this.experienceRepository.findById(experienceId).orElseThrow(() -> new NotFoundException("No experience found with id : " + experienceId));
    }

    @Override
    public Set<Experience> getAllExperienceForUser(Long userId) {
        return this.experienceRepository.fetchAllExperienceForUser(userId);
    }

    @Override
    public Experience saveOrUpdateExperience(Long experienceId, ExperienceDTO experienceDTO) {
        UserResponseDTO userResponseDTO = this.userService.getUserById(experienceDTO.getUserId());
        User user = new User();
        user.setId(userResponseDTO.getId());
        Experience experience = this.modelMapper.map(experienceDTO, Experience.class);
        if (experienceId != null) {
            this.getExperienceById(experienceId);
            experience.setExperienceId(experienceId);
        }
        experience.setUser(user);
        return this.experienceRepository.save(experience);
    }

    @Override
    public Boolean deleteExperienceById(Long experienceId) {
        Experience experience = this.experienceRepository.findById(experienceId).orElse(null);
        if (experience == null) {
            return false;
        }
        this.experienceRepository.deleteById(experienceId);
        return true;
    }

    @Override
    public void deleteAllExperienceForUser(Long userId) {
        this.userService.getUserById(userId);
        this.experienceRepository.deleteAllExperienceForUser(userId);
    }


}
