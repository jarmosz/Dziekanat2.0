package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import com.jarmosz.Dziekanat20.applicants.GenerateStrategyFactory;
import com.jarmosz.Dziekanat20.applicants.TaskSetCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class ApplicantsQueue  {

    @Value("${applicants.queue.size}")
    private int queueSize;

    @Autowired
    private GenerateStrategyFactory generateStrategyFactory;

    @Getter
    @Setter
    private ArrayList<Applicant> applicantsQueue;

    @PostConstruct
    public void initQueue(){
        applicantsQueue = new ArrayList<>();

        int currentSize = 0;
        while(currentSize < queueSize){
            Applicant applicant = new Applicant();
            applicant.setPersonality(generateStrategyFactory.getStrategy().generatePerson());
            applicant.setTasks(TaskSetCreator.createSetOfTask());
            applicantsQueue.add(applicant);
            currentSize += 1;
        }
    }

}
