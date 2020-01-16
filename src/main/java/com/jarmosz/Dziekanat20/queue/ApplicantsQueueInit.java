package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import com.jarmosz.Dziekanat20.applicants.GenerateStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicantsQueueInit {
    @Value("${applicants.queue.size}")
    private int queueSize;

    @Autowired
    private GenerateStrategyFactory generateStrategyFactory;

    @Bean
    private ApplicantsQueue<Applicant> createApplicant(){
        int size = 0;
        ApplicantsQueue<Applicant> applicantsQueue = new ApplicantsQueue<Applicant>();

        while(size < queueSize){
            Applicant applicant = new Applicant();
            applicant.setPersonality(generateStrategyFactory.getStrategy().generatePerson());
            applicantsQueue.offer(applicant);
            size += 1;
        }
        return applicantsQueue;
    }

}
