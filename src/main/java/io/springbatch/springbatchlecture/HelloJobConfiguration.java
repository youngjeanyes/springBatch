package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {

    //의존성 주입 받음
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob(){
        return jobBuilderFactory.get("helloJob")    // job 이름을 "helloJob"으로 부여
                .start(helloStep1())                // 시작 step
                .next(helloStep2())                 // 이어지는 step
                .build();
    }

    @Bean
    public Step helloStep1(){
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        //비즈니스 로직 수행
                        System.out.println(" ======================");
                        System.out.println(" >> Hello Spring Batch!");
                        System.out.println(" ======================");
                        System.out.println(" >> step1 was executed.");
                        System.out.println(" ======================");

                        return RepeatStatus.FINISHED;   // Finished이면 tasklet이 1번말 실행되고 종료. (기본: 무제한 실행됨)
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2(){
        return stepBuilderFactory.get("helloStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println(" ======================");
                        System.out.println(" >> step2 was executed.");
                        System.out.println(" ======================");

                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
