package com.bfcai.topjob.api;

import com.bfcai.topjob.dto.EducationDTO;
import com.bfcai.topjob.model.Education;
import com.bfcai.topjob.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationResource {
    private final EducationService educationService;

    @Autowired
    public EducationResource(EducationService educationService) {
        this.educationService = educationService;
    }


    @GetMapping
    public ResponseEntity<Set<Education>> getAllEducation() {
        return ResponseEntity.ok(this.educationService.getAllEducation());
    }

    @GetMapping(path = "/{educationId}")
    public ResponseEntity<Education> getEducationById(@PathVariable(value = "educationId") Long educationId) {
        return ResponseEntity.ok(this.educationService.getEducationById(educationId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Set<Education>> getAllEducationForUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.educationService.getAllEducationForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Education> saveEducation(@RequestBody EducationDTO educationDTO) {
        Education savedEducation = this.educationService.saveOrUpdateEducation(null, educationDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{educationId}").buildAndExpand(savedEducation.getEducationId()).toUri();
        return ResponseEntity.created(location).body(savedEducation);
    }

    @DeleteMapping(path = "/{educationId}")
    public ResponseEntity<Boolean> deleteEducationById(@PathVariable(value = "educationId") Long educationId) {
        return ResponseEntity.ok(this.educationService.deleteEducationById(educationId));
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<String> deleteAllEducationForUser(@PathVariable(value = "userId") Long userId) {
        this.educationService.deleteAllEducationForUser(userId);
        return ResponseEntity.ok("All education for user with id : " + userId + " deleted successfully");
    }

    @PutMapping(path = "/{educationId}")
    public ResponseEntity<Education> updateEducation(@PathVariable(value = "educationId") Long educationId, @RequestBody EducationDTO educationDTO) {
        return ResponseEntity.ok(this.educationService.saveOrUpdateEducation(educationId, educationDTO));
    }

}
