package com.cesararana.exe.pageapp.application.impl;

import com.cesararana.exe.pageapp.application.Slugger;
import com.cesararana.exe.pageapp.application.PageApp;
import com.cesararana.exe.pageapp.application.PagePersistence;
import com.cesararana.exe.pageapp.application.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PageAppService implements PageApp {

    private final Slugger slugger;
    private final PagePersistence persistence;

    @Override
    public Page save(Page page) {
        Page slugged = page.toBuilder().slug(slugger.slug(page.getDescription())).build();
        Page saved = persistence.save(slugged);
        return saved;
    }

    @Override
    public Optional<Page> get(long id) {
        return persistence.get(id);
    }

    @Override
    public Collection<Page> getAll() {
        return persistence.getAll();
    }
}
