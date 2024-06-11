package vn.sic.project.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vn.sic.core.common.util.DateUtils;
import vn.sic.project.domain.dto.ProjectResourceDTO;

import java.util.Date;

@Entity
@Data
@Table(name = "tbl_project_resource")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false,name = "project_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @Column(name = "resource_id", nullable = false)
    private String resourceId;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "created_date", nullable = false)
    private String createdDate;

    @Column(name = "file_key", nullable = false)
    private String fileKey;

    public ProjectResourceDTO toDTO() {
        return new ProjectResourceDTO(this.resourceId, this.fileUrl,
                this.createdDate, this.fileKey);
    }
}
