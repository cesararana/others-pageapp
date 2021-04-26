package com.cesararana.exe.pageapp.application.impl;

import com.cesararana.exe.pageapp.application.Slugger;
import com.cesararana.exe.pageapp.application.PagePersistence;
import com.cesararana.exe.pageapp.application.domain.DataView;
import com.cesararana.exe.pageapp.application.domain.Page;
import com.cesararana.exe.pageapp.application.domain.Query;
import com.cesararana.exe.pageapp.application.exceptions.PageAppException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;
import javax.xml.crypto.Data;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PageAppServiceTest {

    @InjectMocks
    private PageAppService service;

    @Mock
    private Slugger slugger;
    @Mock
    private PagePersistence persistence;

    @BeforeEach
    public void setup(){
        service = new PageAppService(slugger, persistence);
    }

    @Test
    void save() throws MalformedURLException, PageAppException {

        URL url = new URL("https://example.com");
        Page expected = Page.builder()
                .slug("slugged-description")
                .url(url.toString())
                .description("Slugged Description.")
                .notes("Notes")
                .build();
        Page initial = expected.toBuilder()
                .slug(null)
                .build();

        Mockito.when(slugger.slug(Mockito.anyString())).thenReturn(expected.getSlug());
        Mockito.when(persistence.save(any(Page.class))).thenReturn(expected);

        Page saved = service.save(initial);

        Mockito.verify(slugger, Mockito.atLeastOnce()).slug(Mockito.anyString());
        Mockito.verify(persistence, Mockito.atLeastOnce()).save(any(Page.class));

        assertNotNull(saved);
    }

    @Test
    void get() throws MalformedURLException {
        URL url = new URL("https://example.com");
        Page expected = Page.builder()
                .id(1L)
                .slug("slugged-description")
                .url(url.toString())
                .description("Slugged Description.")
                .notes("Notes")
                .build();

        Mockito.when(persistence.get(any(String.class))).thenReturn(Optional.of(expected));

        Optional<Page> saved = service.get(expected.getSlug());

        Mockito.verify(persistence, Mockito.atLeastOnce()).get(any(String.class));

        assertNotNull(saved);
        assertTrue(saved.isPresent());
    }

    @Test
    void getAll() throws MalformedURLException {

        URL url = new URL("https://example.com");
        Page expected = Page.builder()
                .id(1L)
                .slug("slugged-description")
                .url(url.toString())
                .description("Slugged Description.")
                .notes("Notes")
                .build();
        DataView<Page> dataView = new DataView(
                List.of(expected),
                1,
                1,
                10
        );

        Mockito.when(persistence.getAll(any(Query.class))).thenReturn(dataView);

        DataView<Page> result = service.getPaged(Query.builder().build());

        Mockito.verify(persistence, Mockito.atLeastOnce()).getAll(any(Query.class));

        assertNotNull(result);
        assertFalse(result.getData().isEmpty());
        assertEquals(1, result.getData().size());

        Page firstPage = result.getData().stream().findFirst().get();

        assertEquals(expected.getId(), firstPage.getId());
        assertEquals(expected.getSlug(), firstPage.getSlug());
        assertEquals(expected.getUrl(), firstPage.getUrl());
        assertEquals(expected.getDescription(), firstPage.getDescription());
        assertEquals(expected.getNotes(), firstPage.getNotes());
    }
}