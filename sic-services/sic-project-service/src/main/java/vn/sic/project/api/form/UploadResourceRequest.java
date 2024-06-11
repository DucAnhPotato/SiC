package vn.sic.project.api.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResourceRequest {
    @NotEmpty
    private String resourceId;

    @NotEmpty
    private String fileUrl;

    @NotEmpty
    private String createdDate;

    @NotEmpty
    private String fileKey;
}
