package com.bfcai.topjob.service.implementaion;


import com.bfcai.topjob.dto.SkillDTO;
import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Skill;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.SkillRepository;
import com.bfcai.topjob.service.SkillService;
import com.bfcai.topjob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, UserService userService, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Skill> getAllSkill() {
        return new HashSet<>(this.skillRepository.findAll());
    }

    @Override
    public Skill getSkillById(Long skillId) {
        return this.skillRepository.findById(skillId).orElseThrow(() -> new NotFoundException("No skill found with id : " + skillId));
    }

    @Override
    public Set<Skill> getAllSkillsForUser(Long userId) {
        return this.skillRepository.fetchAllSkillsForUser(userId);
    }

    @Override
    public Skill saveOrUpdateSkill(Long skillId, SkillDTO skillDTO) {
        UserResponseDTO userResponseDTO = this.userService.getUserById(skillDTO.getUserId());
        User user = new User();
        user.setId(userResponseDTO.getId());
        Skill skill = this.modelMapper.map(skillDTO, Skill.class);
        if (skillId != null) {
            this.getSkillById(skillId);
            skill.setSkillId(skillId);
        }
        skill.setUser(user);
        return this.skillRepository.save(skill);
    }

    @Override
    public Boolean deleteSkillById(Long skillId) {
        Skill skill = this.skillRepository.findById(skillId).orElse(null);
        if (skill == null) {
            return false;
        }
        this.skillRepository.deleteById(skillId);
        return true;
    }

    @Override
    public void deleteAllSkillsForUser(Long userId) {
        this.userService.getUserById(userId);
        this.skillRepository.deleteAllSkillsForUser(userId);
    }
}
