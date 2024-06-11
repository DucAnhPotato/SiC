package vn.sic.project.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sic.core.common.exception.ServiceException;
import vn.sic.core.common.util.DateUtils;
import vn.sic.project.api.form.CreateProjectRequest;
import vn.sic.project.api.form.UpdateProjectRequest;
import vn.sic.project.api.form.UploadResourceRequest;
import vn.sic.project.api.reponse.Translator;
import vn.sic.project.application.service.ProjectService;
import vn.sic.project.domain.dto.ProjectDTO;
import vn.sic.project.domain.dto.ProjectResourceDTO;
import vn.sic.project.domain.entity.Project;
import vn.sic.project.domain.entity.ProjectResource;
import vn.sic.project.domain.repository.ProjectRepository;
import vn.sic.project.domain.repository.ProjectResourceRepository;
import vn.sic.project.infrastructure.constant.MessageCode;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectResourceRepository projectResourceRepository;

    @Override
    public ProjectDTO createProject(CreateProjectRequest request) {
        Project project;

        try {
            project = Project
                    .builder()
                    .starterId(request.getStarterId())
                    .name(request.getProjectName())
                    .target(request.getProjectTarget())
                    .address(request.getAddress())
                    .startDate(DateUtils.parseToDate(request.getStartDate(), "yyyyMMddHHmmss"))
                    .endDate(DateUtils.parseToDate(request.getEndDate(), "yyyyMMddHHmmss"))
                    .detail(request.getDetail())
                    .numberOfSupporters(0)
                    .build();
            projectRepository.save(project);


        } catch (Exception e) {
            throw new ServiceException(MessageCode.INTERNAL_SERVER_ERROR.getCode(), Translator.toLocale(MessageCode.INTERNAL_SERVER_ERROR.getMessage()));
        }

        return project.toDTO();

    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        Project project;

        try {
            project = projectRepository.findById(projectId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ServiceException(MessageCode.NOT_FOUND.getCode(), Translator.toLocale(MessageCode.NOT_FOUND.getMessage()));
        }

        return project.toDTO();
    }

    @Override
    public ProjectDTO updateProject(UpdateProjectRequest request) {
        Project preProject;

        try {
            preProject = projectRepository.findById(request.getId()).orElseThrow();

            preProject.setId(request.getId());
            preProject.setStarterId(request.getStarterId());
            preProject.setName(request.getProjectName());
            preProject.setTarget(request.getProjectTarget());
            preProject.setAddress(request.getAddress());
            preProject.setStartDate(DateUtils.parseToDate(request.getStartDate(), "yyyyMMddHHmmss"));
            preProject.setEndDate(DateUtils.parseToDate(request.getEndDate(), "yyyyMMddHHmmss"));
            preProject.setDetail(request.getDetail());

            projectRepository.save(preProject);
        } catch (NoSuchElementException e) {
            throw new ServiceException(MessageCode.NOT_FOUND.getCode(), Translator.toLocale(MessageCode.NOT_FOUND.getMessage()));
        }

        return preProject.toDTO();
    }

    @Override
    public List<ProjectDTO> searchProjectsByName(String search) {
        return projectRepository
                .findAllByNameContainingIgnoreCase(search)
                .stream()
                .map(Project::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResourceDTO uploadResource(UploadResourceRequest request, Long projectId) {
        ProjectResource projectResource;

        try {
            Project project = projectRepository.findById(projectId).orElseThrow();

            projectResource = ProjectResource
                    .builder()
                    .project(project)
                    .resourceId(request.getResourceId())
                    .fileUrl(request.getFileUrl())
                    .createdDate(request.getCreatedDate())
                    .fileKey(request.getFileKey())
                    .build();
            projectResourceRepository.save(projectResource);

        } catch (NoSuchElementException e) {
            throw new ServiceException(MessageCode.NOT_FOUND.getCode(), Translator.toLocale(MessageCode.NOT_FOUND.getMessage()));
        } catch (Exception e) {
            throw new ServiceException(MessageCode.INTERNAL_SERVER_ERROR.getCode(), Translator.toLocale(MessageCode.INTERNAL_SERVER_ERROR.getMessage()));
        }

        return projectResource.toDTO();
    }

    @Override
    public List<ProjectResourceDTO> getAllResourcesByProjectId(Long projectId) {
        Project project;

        try {
            project = projectRepository.findById(projectId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ServiceException(MessageCode.NOT_FOUND.getCode(), Translator.toLocale(MessageCode.NOT_FOUND.getMessage()));
        }

        return projectResourceRepository
                .getProjectResourcesByProject(project)
                .stream()
                .map(ProjectResource::toDTO)
                .collect(Collectors.toList());
    }

}
