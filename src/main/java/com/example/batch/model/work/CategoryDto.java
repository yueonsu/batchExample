package com.example.batch.model.work;

import lombok.Data;

@Data
public class CategoryDto {
    private Long seq;
    private String name;
    private String updateTime;

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .seq(this.seq)
                .name(this.name)
                .updateTime(this.updateTime)
                .build();
    }
}
