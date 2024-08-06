package com.example.batch.batch.category;

import com.example.batch.model.work.CategoryEntity;
import com.example.batch.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryItemProcessor implements ItemProcessor<CategoryEntity, CategoryEntity> {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity process(CategoryEntity item) throws Exception {
        log.info("[Category GC] delete item: {}, seq: {}", item.getName(), item.getSeq());
        categoryRepository.delete(item);
        return item;
    }
}
