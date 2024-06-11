package vn.sic.log.domain.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseLogRequest {
    private String createdAt;
    private String className;
    private String methodName;
    private String requestURL;
    private Object[] requestParam;
    private Object response;
}
