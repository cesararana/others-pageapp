package com.cesararana.exe.pageapp.adapters.entities;

import com.cesararana.exe.pageapp.application.domain.Page;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.net.URL;

@Entity
@Table(name = "url", indexes = @Index(columnList = "slug", unique = true))
@Data
@Builder
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private URL url;

    @NotBlank
    @Column(name = "slug")
    private String slug;
    @NotBlank
    private String description;
    @NotBlank
    private String notes;

    public static UrlEntity from(Page page) {
        return UrlEntity.builder()
                .id(page.getId())
                .url(page.getUrl())
                .slug(page.getSlug())
                .description(page.getDescription())
                .notes(page.getNotes())
                .build();
    }

    public static Page to(UrlEntity urlEntity) {
        return Page.builder()
                .id(urlEntity.getId())
                .url(urlEntity.getUrl())
                .slug(urlEntity.getSlug())
                .description(urlEntity.getDescription())
                .notes(urlEntity.getNotes())
                .build();
    }
}
