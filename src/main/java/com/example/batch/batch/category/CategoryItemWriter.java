package com.example.batch.batch.category;

import com.example.batch.model.work.CategoryEntity;
import com.example.batch.model.work.CategoryHistoryDto;
import com.example.batch.repositories.CategoryHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryItemWriter implements ItemWriter<CategoryEntity> {

    private final CategoryHistoryRepository historyRepository;

    @Override
    public void write(Chunk<? extends CategoryEntity> items) throws Exception {
        for (CategoryEntity item : items) {
            CategoryHistoryDto historyDto = new CategoryHistoryDto();
            historyDto.setSeq(item.getSeq());
            historyDto.setName(item.getName());
            historyDto.setStatus("Delete");

            historyRepository.save(historyDto.toEntity());
        }
    }
}
