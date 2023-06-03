package com.bfcai.topjob.api;


import com.bfcai.topjob.dto.SkillDTO;
import com.bfcai.topjob.model.Skill;
import com.bfcai.topjob.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillResource {
    private final SkillService skillService;

    @Autowired
    public SkillResource(SkillService skillService) {
        this.skillService = skillService;
    }


    @GetMapping
    public ResponseEntity<Set<Skill>> getAllSkill() {
        return ResponseEntity.ok(this.skillService.getAllSkill());
    }

    @GetMapping(path = "/{skillId}")
    public ResponseEntity<Skill> getSkillById(@PathVariable(value = "skillId") Long skillId) {
        return ResponseEntity.ok(this.skillService.getSkillById(skillId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Set<Skill>> getAllSkillForUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.skillService.getAllSkillsForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Skill> saveSkill(@RequestBody SkillDTO skillDTO) {
        Skill savedSkill = this.skillService.saveOrUpdateSkill(null, skillDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{skillId}").buildAndExpand(savedSkill.getSkillId()).toUri();
        return ResponseEntity.created(location).body(savedSkill);
    }

    @DeleteMapping(path = "/{skillId}")
    public ResponseEntity<Boolean> deleteSkillById(@PathVariable(value = "skillId") Long skillId) {
        return ResponseEntity.ok(this.skillService.deleteSkillById(skillId));
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<String> deleteAllSkillsForUser(@PathVariable(value = "userId") Long userId) {
        this.skillService.deleteAllSkillsForUser(userId);
        return ResponseEntity.ok("All skill for user with id : " + userId + " deleted successfully");
    }

    @PutMapping(path = "/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable(value = "skillId") Long skillId, @RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(this.skillService.saveOrUpdateSkill(skillId, skillDTO));
    }

}
