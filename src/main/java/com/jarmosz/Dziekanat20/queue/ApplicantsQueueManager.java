package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Log
@Component
public class ApplicantsQueueManager {
    @Autowired
    private ApplicantsQueueImpl applicantsQueue;

    @PostConstruct
    public void viewQueue(){
        for(Applicant applicant : applicantsQueue.getApplicantsQueue()){
            log.info(applicant.getPersonality().tellSomethingAbout());
        }
    }
}
