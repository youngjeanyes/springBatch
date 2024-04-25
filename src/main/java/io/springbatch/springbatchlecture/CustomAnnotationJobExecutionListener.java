package io.springbatch.springbatchlecture;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class CustomAnnotationJobExecutionListener {
    @BeforeJob
    public void bJob(JobExecution jobExecution) {
        System.out.println("::::: Job is started ::::: ");
        System.out.println("::::: jobName = " + jobExecution.getJobInstance().getJobName());
    }

    @AfterJob
    public void aJob(JobExecution jobExecution) {
        long startTime = jobExecution.getStartTime().getTime();
        long endTime = jobExecution.getEndTime().getTime();

        System.out.println("::::: total spent time= " + (endTime - startTime));
    }
}
