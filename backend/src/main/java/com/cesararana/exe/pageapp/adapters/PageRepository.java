package com.cesararana.exe.pageapp.adapters;

import com.cesararana.exe.pageapp.adapters.entities.PageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends PagingAndSortingRepository<PageEntity, Long> {

    Optional<PageEntity> findBySlug(String slug);
}
