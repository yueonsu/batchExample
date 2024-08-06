package com.example.batch.batch.category;

import com.example.batch.model.work.CategoryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class CategoryJobConfig { // 원래 DefaultBatchConfiguration을 상속받았는데 다중 DB 설정 후 BatchDataSource 적용을 위해 제거
    private final CategoryItemReader categoryItemReader;
    private final CategoryItemWriter categoryItemWriter;
    private final CategoryItemProcessor categoryItemProcessor;

    @Bean
    public Job exampleJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("exampleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(exampleStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step exampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("exampleStep", jobRepository)
                .<CategoryEntity, CategoryEntity>chunk(10, transactionManager)
                .reader(categoryItemReader.createReader())
                .processor(categoryItemProcessor)
                .writer(categoryItemWriter)
                .build();
    }

//    @Bean
//    public JpaPagingItemReader<CategoryEntity> reader() {
//        return new JpaPagingItemReaderBuilder<CategoryEntity>()
//                .name("categoryReader")
//                .entityManagerFactory(entityManagerFactory)
//                .queryString("SELECT c FROM CategoryEntity c")
//                .pageSize(10)
//                .build();
//    }

//    @Bean
//    public ItemWriter<CategoryEntity> writer() {
//        return items ->
//            items.forEach(item -> {
//                CategoryHistoryDto historyDto = new CategoryHistoryDto();
//                historyDto.setSeq(item.getSeq());
//                historyDto.setName(item.getName());
//                historyDto.setStatus("Delete");
//                historyRepository.save(historyDto.toEntity());
//            });
//    }

//    @Bean
//    public ItemProcessor<CategoryEntity, CategoryEntity> processor() {
//        return item -> {
//            log.info("[Category GC] delete item: {}, seq: {}", item.getName(), item.getSeq());
//            categoryRepository.delete(item);
//            return item;
//        };
//    }
}
