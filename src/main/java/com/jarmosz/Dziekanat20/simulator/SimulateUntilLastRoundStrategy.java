package com.jarmosz.Dziekanat20.simulator;

import com.jarmosz.Dziekanat20.employess.DeanOffice;
import com.jarmosz.Dziekanat20.employess.Employee;
import com.jarmosz.Dziekanat20.queue.ApplicantsQueue;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log
public class SimulateUntilLastRoundStrategy implements SimulatorStrategy {

    @Value("${simulator.rounds}")
    private int roundsAmount;

    @Autowired
    private DeanOffice deanOffice;
    @Autowired
    private ApplicantsQueue applicantsQueue;


    @Override
    public void runSimulation() {
        for(int i = 1; i <= roundsAmount; i++){
            applicantsQueue.generateNewApplicants();

            log.info("########### CURRENT ROUND " + i + " ################");
            applicantsQueue.reorderQueue();

            deanOffice.addTasksToEmployeesWithoutTasks();

            for(Employee employee : deanOffice.getEmployees()){
                employee.serveApplicants(applicantsQueue);
            }

            applicantsQueue.addReactionsToApplicants(); 
            applicantsQueue.lookAtTheQueue();
            deanOffice.dismissJob();

            if(deanOffice.checkIfOfficeIsEmpty()){
                break;
            }

        }
    }
}
