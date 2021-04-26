package com.cesararana.exe.pageapp.application.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Query {
    int page;
    int size;
}
