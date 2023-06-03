package com.bfcai.topjob.api;


import com.bfcai.topjob.dto.ExperienceDTO;
import com.bfcai.topjob.dto.ProjectDTO;
import com.bfcai.topjob.model.Experience;
import com.bfcai.topjob.model.Project;
import com.bfcai.topjob.service.ExperienceService;
import com.bfcai.topjob.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectResource {
    private final ProjectService projectService;

    @Autowired
    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping
    public ResponseEntity<Set<Project>> getAllProjects() {
        return ResponseEntity.ok(this.projectService.getAllProjects());
    }

    @GetMapping(path = "/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "projectId") Long projectId) {
        return ResponseEntity.ok(this.projectService.getProjectById(projectId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Set<Project>> getAllProjectsForUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(this.projectService.getAllProjectsForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Project> saveProject(@RequestBody ProjectDTO projectDTO) {
        Project savedProject = this.projectService.saveOrUpdateProject(null, projectDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectId}").buildAndExpand(savedProject.getProjectId()).toUri();
        return ResponseEntity.created(location).body(savedProject);
    }

    @DeleteMapping(path = "/{projectId}")
    public ResponseEntity<Boolean> deleteProjectIdById(@PathVariable(value = "projectId") Long projectId) {
        return ResponseEntity.ok(this.projectService.deleteProjectById(projectId));
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<String> deleteAllProjectsForUser(@PathVariable(value = "userId") Long userId) {
        this.projectService.deleteAllProjectsForUser(userId);
        return ResponseEntity.ok("All projects for user with id : " + userId + " deleted successfully");
    }

    @PutMapping(path = "/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "projectId") Long projectId, @RequestBody ProjectDTO projectDTO) {
        return ResponseEntity.ok(this.projectService.saveOrUpdateProject(projectId, projectDTO));
    }

}
