package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("QueueInit")
public class ApplicantsQueueInit {
    @Autowired
    private ApplicantsQueueImpl applicantsQueue;

    @Autowired
    private Applicant applicant;

    @Value("${applicants.queue.size}")
    private int queueSize;

    @PostConstruct
    private void initWithApplicants(){
        int applicantsInQueue = 0;

        while(applicantsInQueue != queueSize){
            applicant.generatePersonality();
            applicantsQueue.getApplicantsQueue().offer(applicant);
            applicantsInQueue += 1;
        }

    }
}
