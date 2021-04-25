package com.cesararana.exe.pageapp.application;

import lombok.AllArgsConstructor;
import lombok.Data;

public class TestUtils {

    @AllArgsConstructor
    @Data
    public static final class TestData<V, E> {
        private V value;
        private E expected;
    }
}
