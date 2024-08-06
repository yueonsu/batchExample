package com.example.batch.model.work;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tCategory")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nSeq", nullable = false)
    private Long seq;

    @Column(name = "sName", nullable = false)
    private String name;

    @Column(name = "dtUpdateTime", nullable = false)
    private String updateTime;
}
