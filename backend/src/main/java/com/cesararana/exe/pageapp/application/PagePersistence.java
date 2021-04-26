package com.cesararana.exe.pageapp.application;

import com.cesararana.exe.pageapp.application.domain.DataView;
import com.cesararana.exe.pageapp.application.domain.Page;
import com.cesararana.exe.pageapp.application.domain.Query;

import java.util.Optional;

public interface PagePersistence {

    Page save(Page page);
    Optional<Page> get(String slug);
    DataView<Page> getAll(Query query);

}
