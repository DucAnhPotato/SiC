package vn.sic.project.application.service;

import vn.sic.project.api.form.CreateProjectRequest;
import vn.sic.project.api.form.UpdateProjectRequest;
import vn.sic.project.api.form.UploadResourceRequest;
import vn.sic.project.domain.dto.ProjectDTO;
import vn.sic.project.domain.dto.ProjectResourceDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(CreateProjectRequest request);

    ProjectDTO getProjectById(Long projectId);

    ProjectDTO updateProject(UpdateProjectRequest request);

    List<ProjectDTO> searchProjectsByName(String search);

    ProjectResourceDTO uploadResource(UploadResourceRequest request, Long projectId);

    List<ProjectResourceDTO> getAllResourcesByProjectId(Long projectId);
}
