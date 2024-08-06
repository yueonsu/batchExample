package com.example.batch.model.work;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tCategoryHistory")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryHistoryEntity {

    @Id
    @Column(name = "nSeq", nullable = false)
    public Long seq;

    @Column(name = "sName", nullable = false)
    private String name;

    @Column(name = "sStatus", nullable = false)
    private String status;
}
