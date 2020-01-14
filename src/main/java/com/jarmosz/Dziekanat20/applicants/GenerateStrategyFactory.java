package com.jarmosz.Dziekanat20.applicants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GenerateStrategyFactory {

    @Value ("${applicants.injection.variant}")
    private String variant;

    @Autowired
    private GenerateConfiguredApplicantStrategy generateConfiguredApplicantStrategy;

    @Autowired
    private GenerateRandomApplicantStrategy generateRandomApplicantStrategy;

    @Bean
    public GenerateStrategy getStrategy(){
        if(variant.equals("A")){
            return generateRandomApplicantStrategy;
        }
        else {
            return generateConfiguredApplicantStrategy;
        }
    }

}
