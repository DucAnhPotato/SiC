package vn.sic.sample.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "t_sample")
public class SampleEntity {
    @Id
    private Long id;
}
