package com.cesararana.exe.pageapp.application.impl;

import com.cesararana.exe.pageapp.application.Slugger;
import com.cesararana.exe.pageapp.application.PageApp;
import com.cesararana.exe.pageapp.application.PagePersistence;
import com.cesararana.exe.pageapp.application.domain.DataView;
import com.cesararana.exe.pageapp.application.domain.Page;
import com.cesararana.exe.pageapp.application.domain.Query;
import com.cesararana.exe.pageapp.application.exceptions.PageAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
    public Page save(Page page) throws PageAppException {

        page.validate();

        Page slugged = page.toBuilder().slug(slugger.slug(page.getDescription())).build();
        var exists = get(slugged.getSlug());

        if (exists.isPresent()){
            throw new PageAppException("Change description");
        }

        return exists.orElseGet(()-> persistence.save(slugged));

    }

    @Override
    public Optional<Page> get(String slug) {
        return persistence.get(slug);
    }

    @Override
    public DataView<Page> getPaged(Query query) {
        return persistence.getAll(query);
    }
}
