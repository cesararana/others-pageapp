package com.cesararana.exe.pageapp.adapters;

import com.cesararana.exe.pageapp.adapters.entities.UrlEntity;
import com.cesararana.exe.pageapp.application.PagePersistence;
import com.cesararana.exe.pageapp.application.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagePersistenceAdapter implements PagePersistence {

    private PageRepository repository;

    @Override
    public Page save(Page page) {
        UrlEntity saved = repository.save(UrlEntity.from(page));
        return UrlEntity.to(saved);
    }

    @Override
    public Optional<Page> get(long id) {
        return repository.findById(id).map(UrlEntity::to);
    }

    @Override
    public Collection<Page> getAll() {
        return repository.findAll().stream().map(UrlEntity::to).collect(Collectors.toList());
    }
}
