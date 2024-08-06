package com.example.batch.repositories;

import com.example.batch.model.work.CategoryHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryHistoryRepository extends JpaRepository<CategoryHistoryEntity, Long> {

}
