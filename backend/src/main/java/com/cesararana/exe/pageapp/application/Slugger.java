package com.cesararana.exe.pageapp.application;

public interface Slugger {
    /**
     * Slug a string given this restrictions:
     *
     * The following special characters should not be removed, and instead should be replaced with their English
     * representation:
     *
     *     & -> and
     *     @ -> at
     *     % -> percent
     *
     * All other special/non-alphanumeric characters should be removed.
     *
     * The output should never result in multiple consecutive hyphen separators, or hyphen separators at the beginning
     * or end of the slug.
     *
     * @param value to slug
     * @return slug of value
     */
    String slug(String value);
}
