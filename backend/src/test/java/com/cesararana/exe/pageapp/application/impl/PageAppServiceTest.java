package com.cesararana.exe.pageapp.application.impl;

import com.cesararana.exe.pageapp.application.Slugger;
import com.cesararana.exe.pageapp.application.PagePersistence;
import com.cesararana.exe.pageapp.application.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void save() throws MalformedURLException {

        URL url = new URL("https://example.com");
        Page expected = Page.builder()
                .slug("slugged-description")
                .url(url)
                .description("Slugged Description.")
                .notes("Notes")
                .build();
        Page initial = expected.toBuilder()
                .slug(null)
                .build();

        Mockito.when(slugger.slug(Mockito.anyString())).thenReturn(expected.getSlug());
        Mockito.when(persistence.save(Mockito.any(Page.class))).thenReturn(expected);

        Page saved = service.save(initial);

        Mockito.verify(slugger, Mockito.atLeastOnce()).slug(Mockito.anyString());
        Mockito.verify(persistence, Mockito.atLeastOnce()).save(Mockito.any(Page.class));

        assertNotNull(saved);
    }

    @Test
    void get() throws MalformedURLException {
        URL url = new URL("https://example.com");
        Page expected = Page.builder()
                .id(1L)
                .slug("slugged-description")
                .url(url)
                .description("Slugged Description.")
                .notes("Notes")
                .build();

        Mockito.when(persistence.get(Mockito.any(Long.class))).thenReturn(Optional.of(expected));

        Optional<Page> saved = service.get(1L);

        Mockito.verify(persistence, Mockito.atLeastOnce()).get(Mockito.any(Long.class));

        assertNotNull(saved);
        assertTrue(saved.isPresent());
    }

    @Test
    void getAll() {
    }
}