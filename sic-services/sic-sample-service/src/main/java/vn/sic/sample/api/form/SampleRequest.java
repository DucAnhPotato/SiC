package vn.sic.sample.api.form;

import vn.sic.core.common.validation.NotBlank;

public class SampleRequest {
    @NotBlank
    private String requestString;
}
