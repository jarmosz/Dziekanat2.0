package com.jarmosz.Dziekanat20.employess;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import com.jarmosz.Dziekanat20.applicants.ApplicantType;
import com.jarmosz.Dziekanat20.applicants.Task;
import com.jarmosz.Dziekanat20.queue.ApplicantsQueue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@Log
public class Employee {

    @Builder.Default
    private ArrayList<EmployeeTask> tasks;
    private long id;
    private boolean isHired;
    private boolean stoppedByDean;
    private int howLongServDean;
    private double maxApplicantsTasksDifficulty;

    public void serveApplicants(ApplicantsQueue applicants){
        if(!applicants.checkIfApplicantsQueueEmpty()) {
            double totalDifficulty = 0.0;
            ArrayList<Applicant> applicantsQueue = applicants.getApplicantsQueue();
            Map<Applicant, Double> applicantsToServe = applicantsQueue
                    .stream()
                    .collect(
                            Collectors.toMap(applicant -> applicant,
                                    applicant -> applicant.getTasks()
                                            .stream()
                                            .mapToDouble(Task::getDifficulty)
                                            .sum()));
            if (!stoppedByDean) {
                switch (tasks.get(0)) {
                    case WORKING:
                        for (Map.Entry<Applicant, Double> servedApplicant : applicantsToServe.entrySet()) {
                                if (totalDifficulty + servedApplicant.getValue() < maxApplicantsTasksDifficulty) {
                                    totalDifficulty += servedApplicant.getValue();
                                    applicantsQueue.removeIf(applicant -> applicant.equals(servedApplicant.getKey()));
                                } else {
                                    break;
                                }
                        }
                        tasks.remove(0);
                    default:
                        if(applicantsQueue.size() > 0) {
                            Applicant firstApplicant = applicantsQueue.get(0);
                            if (firstApplicant.getPersonality().sayType() == ApplicantType.DEAN) {
                                isHired = false;
                                applicantsQueue.remove(firstApplicant);
                            }
                            tasks.remove(0);
                        }
                }
            } else {
                howLongServDean -= 1;
            }
        }
    }

}
