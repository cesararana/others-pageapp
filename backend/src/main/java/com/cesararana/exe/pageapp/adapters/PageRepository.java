package com.cesararana.exe.pageapp.adapters;

import com.cesararana.exe.pageapp.adapters.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<UrlEntity, Long> {
}
