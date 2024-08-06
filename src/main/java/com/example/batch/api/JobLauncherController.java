package com.example.batch.api;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/job/launcher")
@RequiredArgsConstructor
public class JobLauncherController {
    private final JobLauncher jobLauncher;
    private final Map<String, Job> jobs;

    @GetMapping("/{jobName}")
    public void launchJob(@PathVariable String jobName) throws Exception {
        Job job = Optional.ofNullable(jobs.get(jobName)).orElseThrow(() -> new BadRequestException(jobName + " is not exist"));
        JobParametersBuilder jobParam = new JobParametersBuilder()
                .addLong("date", System.currentTimeMillis());

        jobLauncher.run(job, jobParam.toJobParameters());
    }
}
