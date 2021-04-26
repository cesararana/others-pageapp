package com.cesararana.exe.pageapp.adapters;

import com.cesararana.exe.pageapp.adapters.entities.PageEntity;
import com.cesararana.exe.pageapp.application.PagePersistence;
import com.cesararana.exe.pageapp.application.domain.DataView;
import com.cesararana.exe.pageapp.application.domain.Page;
import com.cesararana.exe.pageapp.application.domain.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagePersistenceAdapter implements PagePersistence {


    private final PageRepository repository;

    @Override
    public Page save(Page page) {
        PageEntity saved = repository.save(PageEntity.from(page));
        return saved.toPage();
    }

    @Override
    public Optional<Page> get(String slug) {
        return repository.findBySlug(slug).map(PageEntity::toPage);
    }

    @Override
    public DataView<Page> getAll(Query query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by(Sort.Order.desc("id")));
        var result = repository.findAll(pageable);
        var pages = result.stream().map(PageEntity::toPage).collect(Collectors.toList());
        return new DataView<>(
            pages,
            result.getTotalElements(),
            query.getPage(),
            pages.size()
        );
    }
}
