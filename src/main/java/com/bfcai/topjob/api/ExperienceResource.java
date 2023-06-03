package com.bfcai.topjob.api;

import com.bfcai.topjob.dto.ExperienceDTO;
import com.bfcai.topjob.model.Experience;
import com.bfcai.topjob.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/experience")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienceResource {
    private final ExperienceService experienceService;

    @Autowired
    public ExperienceResource(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }


    @GetMapping
    public ResponseEntity<Set<Experience>> getAllExperience() {
        return ResponseEntity.ok(this.experienceService.getAllExperience());
    }

    @GetMapping(path = "/{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable(value = "experienceId") Long experienceId) {
        return ResponseEntity.ok(this.experienceService.getExperienceById(experienceId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Set<Experience>> getAllExperienceForUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.experienceService.getAllExperienceForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Experience> saveExperience(@RequestBody ExperienceDTO experienceDTO) {
        Experience savedExperience = this.experienceService.saveOrUpdateExperience(null, experienceDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{experienceId}").buildAndExpand(savedExperience.getExperienceId()).toUri();
        return ResponseEntity.created(location).body(savedExperience);
    }

    @DeleteMapping(path = "/{experienceId}")
    public ResponseEntity<Boolean> deleteExperienceById(@PathVariable(value = "experienceId") Long experienceId) {
        return ResponseEntity.ok(this.experienceService.deleteExperienceById(experienceId));
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<String> deleteAllExperienceForUser(@PathVariable(value = "userId") Long userId) {
        this.experienceService.deleteAllExperienceForUser(userId);
        return ResponseEntity.ok("All experience for user with id : " + userId + " deleted successfully");
    }

    @PutMapping(path = "/{experienceId}")
    public ResponseEntity<Experience> updateExperience(@PathVariable(value = "experienceId") Long experienceId, @RequestBody ExperienceDTO experienceDTO) {
        return ResponseEntity.ok(this.experienceService.saveOrUpdateExperience(experienceId, experienceDTO));
    }
}
