package com.cesararana.exe.pageapp.adapters.entities;

import com.cesararana.exe.pageapp.application.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "page", indexes = @Index(columnList = "slug", unique = true))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    @Column(name = "slug")
    private String slug;
    private String description;
    private String notes;

    public static PageEntity from(Page page) {
        return PageEntity.builder()
                .id(page.getId())
                .url(page.getUrl())
                .slug(page.getSlug())
                .description(page.getDescription())
                .notes(page.getNotes())
                .build();
    }

    public Page toPage() {
        return Page.builder()
                .id(getId())
                .url(getUrl())
                .slug(getSlug())
                .description(getDescription())
                .notes(getNotes())
                .build();
    }
}
