package com.cesararana.exe.pageapp.application.domain;

import lombok.Builder;
import lombok.Getter;

import java.net.URL;

@Getter
@Builder(toBuilder = true)
public class Page {

    private final Long id;

    private final URL url;
    private final String description;
    private final String notes;
    private final String slug;
}
