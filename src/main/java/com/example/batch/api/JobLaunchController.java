package com.example.batch.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/job/launcher")
@RequiredArgsConstructor
public class JobLaunchController {
    private final JobLauncher jobLauncher;
    private final Map<String, Job> jobs;

    @GetMapping("/{jobName}")
    public void launchJob(@PathVariable String jobName) {
        try {
            Job job = Optional.ofNullable(jobs.get(jobName)).orElseThrow(() -> new BadRequestException(jobName + " is not exist"));
            JobParametersBuilder jobParam = new JobParametersBuilder()
                    .addLong("date", System.currentTimeMillis());

            jobLauncher.run(job, jobParam.toJobParameters());
        } catch (BadRequestException e) {
            log.error("[launchJob] {} is not exist!, {}", jobName, e.getMessage());
        } catch (Exception e) {
            log.error("[launchJob] exception: {}", e.getMessage());
        }
    }
}
