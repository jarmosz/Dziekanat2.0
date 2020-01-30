package com.jarmosz.Dziekanat20.applicants;

import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
@Setter
@Getter
public class TaskSetCreator {

    private static Double tasksDifficulty;

    @Value("${applicants.tasks.difficulty}")
    public void TaskSetCreator(double tasksDifficulty){
        this.tasksDifficulty = tasksDifficulty;
    }

    public static ArrayList<Task> createSetOfTask(){
        Random random = new Random();
        int taskAmount = ThreadLocalRandom.current().nextInt(1, 3 + 1);

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
