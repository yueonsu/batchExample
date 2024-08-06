package com.example.batch.model.work;

import lombok.Data;

@Data
public class CategoryHistoryDto {
    public Long seq;
    private String name;
    private String status;

    public CategoryHistoryEntity toEntity() {
        return CategoryHistoryEntity.builder()
                .seq(this.seq)
                .name(this.name)
                .status(this.status)
                .build();
    }
}
