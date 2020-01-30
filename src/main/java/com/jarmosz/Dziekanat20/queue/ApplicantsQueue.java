package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import com.jarmosz.Dziekanat20.applicants.GenerateStrategyFactory;
import com.jarmosz.Dziekanat20.applicants.TaskSetCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;

@Component
@Getter
@Setter
@Log
public class ApplicantsQueue  {

    @Value("${applicants.queue.size}")
    private int queueSize;

    @Value("${applicants.new}")
    private int newApplicantsAmount;

    @Autowired
    private GenerateStrategyFactory generateStrategyFactory;

    private ArrayList<Applicant> applicantsQueue;
    private double totalComplainTime;
    private double totalTasksSum;

    @PostConstruct
    public void initQueue(){
        applicantsQueue = new ArrayList<>();
        addApplicants(queueSize);
        reorderQueue();
        log.info("^^^^^^^^^^^^^^^");
        log.info("########### STARTER QUEUE ###########");
        for(Applicant applicant : applicantsQueue){
            log.info(applicant.getPersonality().tellSomethingAbout());
        }
        log.info("^^^^^^^^^^^^^^^");
    }

    public void addApplicants(int amount){
        for(int i = 0; i < amount; i++){
            Applicant applicant = new Applicant();
            applicant.setPersonality(generateStrategyFactory.getStrategy().generatePerson());
            applicant.setTasks(TaskSetCreator.createSetOfTask());
            applicantsQueue.add(applicant);
        }
    }

    public void generateNewApplicants(){
        addApplicants(newApplicantsAmount);
    }

    public void lookAtTheQueue(){
        log.info("^^^^^^^^^^^^^^^");
        log.info("After round, in the queue, we have " + applicantsQueue.size() + " applicants, in details:");
        for(Applicant applicant : applicantsQueue){
            log.info(applicant.getPersonality().tellSomethingAbout());
        }
        log.info("^^^^^^^^^^^^^^^");

    }

    public void reorderQueue() {
        applicantsQueue.sort(Comparator.comparingDouble((Applicant applicant) -> applicant.getPersonality().howIAmImportantHere()).reversed());
    }

    // Add another round to people, who has to wait and react on waiting
    public void addReactionsToApplicants(){
        for(Applicant applicant : applicantsQueue){
            applicant.getPersonality().waitOneMoreRound();
            applicant.getPersonality().reactOnWaiting();
        }
    }

    public boolean checkIfApplicantsQueueEmpty(){
        if(applicantsQueue.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

}
