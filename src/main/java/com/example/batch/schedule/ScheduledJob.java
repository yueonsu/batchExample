package com.example.batch.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ScheduledJob {

    private final Map<String, Job> jobs;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0/10 * * * * *")
    public void categoryGcScheduleJob() throws Exception {
        String jobName = "exampleJob";
        Job job = Optional.ofNullable(jobs.get(jobName)).orElseThrow(() -> new BadRequestException(jobName + " is not exist"));
        JobParametersBuilder jobParam = new JobParametersBuilder()
                .addLong("date", System.currentTimeMillis());
        jobLauncher.run(job, jobParam.toJobParameters());
    }
}
