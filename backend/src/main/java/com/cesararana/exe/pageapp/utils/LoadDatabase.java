package com.cesararana.exe.pageapp.utils;

import com.cesararana.exe.pageapp.adapters.PageRepository;
import com.cesararana.exe.pageapp.adapters.entities.PageEntity;
import com.cesararana.exe.pageapp.application.impl.SluggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PageRepository repository, SluggerService slugger) {

        return args -> {
            int count = 37;
            while (count > 0) {
                count--;
                String desc = String.format("google search engine %s", count);
                log.info("Preloading " + repository.save(PageEntity.builder()
                        .description(desc)
                        .notes("Preloaded")
                        .url("https://www.google.com")
                        .slug(slugger.slug(desc))
                        .build()));

            }

        };
    }
}
