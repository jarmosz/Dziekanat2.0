package com.jarmosz.Dziekanat20.simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class Simulator {

    @Value("${applicants.injection.variant}")
    private String variant;

    @Autowired
    private SimulateUntilLastRoundStrategy simulateUntilLastRoundStrategy;

    @Autowired
    private SimulateUntilQueueNotEmptyStrategy simulateUntilQueueNotEmptyStrategy;

    @PostConstruct
    public void runStrategy(){
        if(variant.equals("A")){
            simulateUntilLastRoundStrategy.runSimulation();
        }
        else {
            simulateUntilQueueNotEmptyStrategy.runSimulation();
        }
    }


}
