package com.cesararana.exe.pageapp.application;

import com.cesararana.exe.pageapp.application.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface PagePersistence {

    Page save(Page page);
    Optional<Page> get(long id);
    Collection<Page> getAll();

}
