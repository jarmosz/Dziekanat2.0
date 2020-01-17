package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;

@Log
@Component
public class ApplicantsQueueManager {

    @Autowired
    private ApplicantsQueue applicantsQueue;

    @PostConstruct
    public void lookAtQueue() {
        for (Applicant applicant : applicantsQueue.getApplicantsQueue()) {
            log.info(applicant.getPersonality().tellSomethingAbout());
        }
    }

    public void reorderQueue() {
//        Sort queue via priority
        applicantsQueue.getApplicantsQueue().sort(Comparator.comparingDouble(ap -> ap.getPersonality().howIAmImportantHere()));
    }
}