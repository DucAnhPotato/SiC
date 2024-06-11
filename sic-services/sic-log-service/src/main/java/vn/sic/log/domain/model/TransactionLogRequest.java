package vn.sic.log.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.sic.log.domain.model.base.BaseLogRequest;

@Document(collection = "TRANSACTION")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLogRequest extends BaseLogRequest {
    @Id
    private String id;
}
