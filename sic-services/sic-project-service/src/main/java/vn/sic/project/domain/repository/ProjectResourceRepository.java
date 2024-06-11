package vn.sic.project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sic.project.domain.entity.Project;
import vn.sic.project.domain.entity.ProjectResource;

import java.util.List;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long> {

    List<ProjectResource> getProjectResourcesByProject(Project project);
}
