package vn.sic.project.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ProjectDTO(
        String projectName,
        BigDecimal projectTarget,
        String address,
        String startDate,
        String endDate,
        String detail
) {
}
