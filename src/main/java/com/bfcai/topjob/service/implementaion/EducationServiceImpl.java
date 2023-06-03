package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.EducationDTO;
import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Education;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.EducationRepository;
import com.bfcai.topjob.repository.UserRepository;
import com.bfcai.topjob.service.EducationService;
import com.bfcai.topjob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository, UserService userService, ModelMapper modelMapper) {
        this.educationRepository = educationRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Education> getAllEducation() {
        return new HashSet<>(this.educationRepository.findAll());
    }

    @Override
    public Education getEducationById(Long educationId) {
        return this.educationRepository.findById(educationId).orElseThrow(() -> new NotFoundException("No education found with id : " + educationId));
    }

    @Override
    public Set<Education> getAllEducationForUser(Long userId) {
        return this.educationRepository.fetchAllEducationForUser(userId);
    }

    @Override
    public Education saveOrUpdateEducation(Long educationId, EducationDTO educationDTO) {
        UserResponseDTO userResponseDTO = this.userService.getUserById(educationDTO.getUserId());
        User user = new User();
        user.setId(userResponseDTO.getId());
        Education education = this.modelMapper.map(educationDTO, Education.class);
        if (educationId != null) {
            this.getEducationById(educationId);
            education.setEducationId(educationId);
        }
        education.setUser(user);
        return this.educationRepository.save(education);
    }

    @Override
    public Boolean deleteEducationById(Long educationId) {
        Education education = this.educationRepository.findById(educationId).orElse(null);
        if (education == null) {
            return false;
        }
        this.educationRepository.deleteById(educationId);
        return true;
    }

    @Override
    public void deleteAllEducationForUser(Long userId) {
        this.userService.getUserById(userId);
        this.educationRepository.deleteAllEducationForUser(userId);
    }
}
