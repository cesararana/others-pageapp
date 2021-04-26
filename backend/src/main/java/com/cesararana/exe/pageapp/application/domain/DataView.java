package com.cesararana.exe.pageapp.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
@Builder
public class DataView <T>{

    Collection<T> data;
    long total;
    long page;
    long pageSize;

}
