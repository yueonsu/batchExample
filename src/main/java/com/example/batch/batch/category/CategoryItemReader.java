package com.example.batch.batch.category;

import com.example.batch.model.work.CategoryEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryItemReader {

    private final EntityManagerFactory entityManagerFactory;

    public JpaPagingItemReader<CategoryEntity> createReader() {
        LocalDate now = LocalDate.now();
        String date = now + " 00:00:00";
        return new JpaPagingItemReaderBuilder<CategoryEntity>()
                .name("categoryReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT c FROM CategoryEntity c WHERE c.updateTime < '" + date + "'")
                .pageSize(10)
                .build();
    }

}
