package vn.sic.project.api.form;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.sic.core.common.validation.ValidTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequest {
    @NotNull
    private Long starterId;
    @NotEmpty
    @Size(max = 100, message = "max length exceeded")
    private String projectName;
    @NotNull
    @Digits(integer = 18, fraction = 2)
    private BigDecimal projectTarget;
    @NotEmpty
    @Size(max = 100, message = "max length exceeded")
    private String address;
    @NotEmpty
    @ValidTimestamp
    private String startDate;
    @NotEmpty
    @ValidTimestamp
    private String endDate;

    private String detail;

}
