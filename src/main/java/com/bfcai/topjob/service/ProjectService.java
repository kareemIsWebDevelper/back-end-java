package com.bfcai.topjob.service;

import com.bfcai.topjob.dto.ProjectDTO;
import com.bfcai.topjob.model.Project;

import java.util.Set;

public interface ProjectService {
    Set<Project> getAllProjects();

    Project getProjectById(Long projectId);

    Set<Project> getAllProjectsForUser(Long userId);

    Project saveOrUpdateProject(Long projectId, ProjectDTO projectDTO);

    Boolean deleteProjectById(Long projectId);

    void deleteAllProjectsForUser(Long userId);
}
