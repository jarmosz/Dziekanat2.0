package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import com.jarmosz.Dziekanat20.applicants.ApplicantType;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Log
@Component
public class ApplicantsQueueManager {

    @Autowired
    private ApplicantsQueue<Applicant> applicantsQueue;

    public void viewQueueAfterInit() {
        for (Applicant applicant : applicantsQueue) {
            log.info(applicant.getPersonality().tellSomethingAbout());
        }
    }
    @PostConstruct
    public void reorderQueue() {

        boolean ifKindStudentExist = applicantsQueue
                .stream()
                .anyMatch(applicant -> applicant.getPersonality().howIAmImportantHere() == 4.5);

        if(ifKindStudentExist){

        }
        else {

        }

        List<Applicant> fiendsList = applicantsQueue
                .stream()
                .filter(applicant -> applicant.getPersonality().sayType() == ApplicantType.FRIEND)
                .collect(Collectors.toList());

        List<Applicant> notFriendsList = applicantsQueue
                .stream()
                .filter(applicant -> applicant.getPersonality().sayType() != ApplicantType.FRIEND)
                .collect(Collectors.toList());

        notFriendsList.sort(
                (applicant1, applicant2) ->
                        Double.compare(applicant1.getPersonality().howIAmImportantHere(), applicant2.getPersonality().howIAmImportantHere())
        );

        for(Applicant applicant : notFriendsList){
            log.info(applicant.getPersonality().tellSomethingAbout());
        }
    }
}