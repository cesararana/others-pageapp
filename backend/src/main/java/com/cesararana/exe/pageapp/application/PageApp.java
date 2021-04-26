package com.cesararana.exe.pageapp.application;

import com.cesararana.exe.pageapp.application.domain.DataView;
import com.cesararana.exe.pageapp.application.domain.Page;
import com.cesararana.exe.pageapp.application.domain.Query;
import com.cesararana.exe.pageapp.application.exceptions.PageAppException;

import java.util.Optional;

public interface PageApp {
    Page save(Page page) throws PageAppException;
    Optional<Page> get(String slug);
    DataView<Page> getPaged(Query query);
}
