package com.bfcai.topjob.service.implementaion;

import com.bfcai.topjob.dto.ProjectDTO;
import com.bfcai.topjob.dto.UserResponseDTO;
import com.bfcai.topjob.exception.NotFoundException;
import com.bfcai.topjob.model.Project;
import com.bfcai.topjob.model.User;
import com.bfcai.topjob.repository.ProjectRepository;
import com.bfcai.topjob.service.ProjectService;
import com.bfcai.topjob.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserService userService, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<Project> getAllProjects() {
        return new HashSet<>(this.projectRepository.findAll());
    }

    @Override
    public Project getProjectById(Long projectId) {
        return this.projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("No project found with id : " + projectId));
    }

    @Override
    public Set<Project> getAllProjectsForUser(Long userId) {
        return this.projectRepository.fetchAllProjectsForUser(userId);
    }

    @Override
    public Project saveOrUpdateProject(Long projectId, ProjectDTO projectDTO) {
        UserResponseDTO userResponseDTO = this.userService.getUserById(projectDTO.getUserId());
        User user = new User();
        user.setId(userResponseDTO.getId());
        Project project = this.modelMapper.map(projectDTO, Project.class);
        if (projectId != null) {
            this.getProjectById(projectId);
            project.setProjectId(projectId);
        }
        project.setUser(user);
        return this.projectRepository.save(project);
    }

    @Override
    public Boolean deleteProjectById(Long projectId) {
        Project project=this.projectRepository.findById(projectId).orElse(null);
        if(project==null){
            return false;
        }
        this.projectRepository.deleteById(projectId);
        return true;
    }

    @Override
    public void deleteAllProjectsForUser(Long userId) {
        this.userService.getUserById(userId);
        this.projectRepository.deleteAllProjectsForUser(userId);
    }
}
