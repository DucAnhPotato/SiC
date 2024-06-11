package vn.sic.project.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vn.sic.core.common.util.DateUtils;
import vn.sic.core.common.validation.ValidTimestamp;
import vn.sic.project.domain.dto.ProjectDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starter_id", nullable = false)
    private Long starterId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "target", nullable = false)
    private BigDecimal target;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "number_of_supporters", nullable = false)
    private int numberOfSupporters;

    public ProjectDTO toDTO() {
        return new ProjectDTO(this.name, this.target, this.address,
                DateUtils.formatDate(this.startDate, "yyyyMMddHHmmss"),
                DateUtils.formatDate(this.endDate, "yyyyMMddHHmmss"), this.detail);
    }

}
