package com.cesararana.exe.pageapp.application.domain;

import com.cesararana.exe.pageapp.application.validators.URLValidator;
import com.cesararana.exe.pageapp.common.SelfValidator;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder(toBuilder = true)
public class Page extends SelfValidator<Page> {

    Long id;

    @NotBlank
    @URLValidator
    String url;
    @NotBlank
    String description;
    @NotNull
    String notes;

    String slug;
}
