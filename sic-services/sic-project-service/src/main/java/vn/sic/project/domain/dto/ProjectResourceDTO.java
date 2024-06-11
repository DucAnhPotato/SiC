package vn.sic.project.domain.dto;

public record ProjectResourceDTO(
        String resourceId,
        String fileUrl,
        String createdDate,
        String fileKey
) {

}
