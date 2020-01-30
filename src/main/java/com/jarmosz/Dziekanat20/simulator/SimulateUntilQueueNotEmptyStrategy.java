package com.jarmosz.Dziekanat20.simulator;

import com.jarmosz.Dziekanat20.employess.DeanOffice;
import com.jarmosz.Dziekanat20.employess.Employee;
import com.jarmosz.Dziekanat20.queue.ApplicantsQueue;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Component
public class SimulateUntilQueueNotEmptyStrategy implements SimulatorStrategy {

    @Autowired
    private DeanOffice deanOffice;

    @Autowired
    private ApplicantsQueue applicantsQueue;

    private int roundCounter;

    @Override
    public void runSimulation() {
        roundCounter = 0;
        while(!applicantsQueue.checkIfApplicantsQueueEmpty() && !deanOffice.checkIfOfficeIsEmpty()){
            applicantsQueue.generateNewApplicants();

            applicantsQueue.reorderQueue();

            log.info("########### CURRENT ROUND " + roundCounter + " ################");

            deanOffice.addTasksToEmployeesWithoutTasks();

            for(Employee employee : deanOffice.getEmployees()){
                employee.serveApplicants(applicantsQueue);
            }

            applicantsQueue.addReactionsToApplicants();
            applicantsQueue.lookAtTheQueue();
            deanOffice.dismissJob();
        }
    }
}
