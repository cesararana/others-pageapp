package com.cesararana.exe.pageapp.adapters.entities;

import com.cesararana.exe.pageapp.application.domain.Page;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class PageEntityTest {

    @Test
    void from() throws MalformedURLException {

        URL url = new URL("http://example.com");
        Page page = Page.builder()
                .id(1L)
                .url(url.toString())
                .description("Example Description")
                .notes("Notes from example")
                .slug("example-description")
                .build();

        PageEntity entity = PageEntity.from(page);

        assertEquals(page.getId(), entity.getId());
        assertEquals(page.getUrl(), entity.getUrl());
        assertEquals(page.getDescription(), entity.getDescription());
        assertEquals(page.getNotes(), entity.getNotes());
        assertEquals(page.getSlug(), entity.getSlug());

    }

    @Test
    void toPage() throws MalformedURLException {
        URL url = new URL("http://example.com");
        PageEntity entity = PageEntity.builder()
                .id(1L)
                .url(url.toString())
                .description("Example Description")
                .notes("Notes from example")
                .slug("example-description")
                .build();

        Page page = entity.toPage();

        assertEquals(entity.getId(), page.getId());
        assertEquals(entity.getUrl(), page.getUrl());
        assertEquals(entity.getDescription(), page.getDescription());
        assertEquals(entity.getNotes(), page.getNotes());
        assertEquals(entity.getSlug(), page.getSlug());
    }
}