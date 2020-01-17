package com.jarmosz.Dziekanat20.applicants;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class TaskSetCreator {


    private static Double tasksDifficulty;

    @Value("${applicants.tasks.difficulty}")
    public void TaskSetCreator(double tasksDifficulty){
        this.tasksDifficulty = tasksDifficulty;
    }

    public static ArrayList<Task> createSetOfTask(){
        Random random = new Random();
        double taskAmount = (double) random.nextInt(4);

        ArrayList<Task> tasks = new ArrayList<>();
        for(int i = 0; i < taskAmount; i++){
            double singleTaskDifficulty = 1.0 + (tasksDifficulty/taskAmount  - 1.0) * random.nextDouble();
            tasks.add(Task
                    .builder()
                    .difficulty(singleTaskDifficulty)
                    .build());
        }

        return tasks;
    }
}
