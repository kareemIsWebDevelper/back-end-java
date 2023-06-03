package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.SkillDTO;
import com.bfcai.topjob.model.Skill;

import java.util.Set;

public interface SkillService {

    Set<Skill> getAllSkill();
    Skill getSkillById(Long skillId);


    Set<Skill> getAllSkillsForUser(Long userId);

    Skill saveOrUpdateSkill(Long skillId, SkillDTO skillDTO);

    Boolean deleteSkillById(Long skillId);

    void deleteAllSkillsForUser(Long userId);
}
