package vn.sic.project.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.sic.core.common.reponse.ApiResponse;
import vn.sic.core.common.reponse.SuccessResponse;
import vn.sic.project.api.form.CreateProjectRequest;
import vn.sic.project.api.form.UpdateProjectRequest;
import vn.sic.project.api.form.UploadResourceRequest;
import vn.sic.project.api.reponse.Translator;
import vn.sic.project.application.service.ProjectService;
import vn.sic.project.infrastructure.constant.MessageCode;

@RestController
@RequestMapping()
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Create a new project
     *
     * @param  request project request
     * @return SuccessResponse
     */
    @PostMapping("/add")
    public ApiResponse store(@RequestBody @Valid CreateProjectRequest request) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), projectService.createProject(request));
    }

    /**
     * Create a new project
     *
     * @param  projectId project ID
     * @return SuccessResponse
     */
    @GetMapping("/{id}")
    public ApiResponse getProjectById(@PathVariable(value = "id") Long projectId) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), projectService.getProjectById(projectId));
    }

    /**
     * Update an existing project
     *
     * @param  request update project request
     * @return SuccessResponse
     */
    @PutMapping("/update")
    public ApiResponse updateProject(@RequestBody @Valid UpdateProjectRequest request) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), projectService.updateProject(request));
    }

    /**
     * Search for projects by name
     *
     * @param  search project name
     * @return SuccessResponse
     */
    @GetMapping
    public ApiResponse searchProjectsByName(@RequestParam(value = "s") String search) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), projectService.searchProjectsByName(search));
    }

    /**
     * Upload resource
     *
     * @param  request resource request
     * @param  projectId project ID
     * @return SuccessResponse
     */
    @PostMapping("/{id}/uploadFile")
    public ApiResponse uploadResource(@RequestBody @Valid UploadResourceRequest request,
                                      @PathVariable(value = "id") Long projectId) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), projectService.uploadResource(request, projectId));
    }

    /**
     * Get all resources by project ID
     *
     * @param  projectId project ID
     * @return SuccessResponse
     */
    @GetMapping("/{id}/resources")
    public ApiResponse getResourcesByProjectId(@PathVariable(value = "id") Long projectId) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), projectService.getAllResourcesByProjectId(projectId));
    }
}
