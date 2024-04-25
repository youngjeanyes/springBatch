package io.springbatch.springbatchlecture;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {

        //name으로 저장된 값이 없을 경우
        if(parameters.getString("name") == null){
            throw new JobParametersInvalidException("name parameter is not found");
        }

    }
}
