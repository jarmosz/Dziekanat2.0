package com.jarmosz.Dziekanat20.employess;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@Log
@Getter
@Setter
public class DeanOffice {
    @Value("${employees.amount}")
    private int jobPositionsAmount;

    @Value("#{'${employees.tasks}'.split(' ')}")
    private ArrayList<EmployeeTask> tasks;

    @Value("${applicants.tasks.difficulty}")
    private double maxApplicantsTasksDifficulty;

    private ArrayList<Employee> employees;

    @PostConstruct
    public void createEmployees(){
        employees = new ArrayList<Employee>();
        for(int i = 0; i < jobPositionsAmount; i++){
            ArrayList<EmployeeTask> tasksForEmployee = new ArrayList<EmployeeTask>(tasks);
            Employee newEmployee = Employee
                    .builder()
                    .id(i)
                    .isHired(true)
                    .stoppedByDean(false)
                    .howLongServDean(4)
                    .tasks(tasksForEmployee)
                    .maxApplicantsTasksDifficulty(maxApplicantsTasksDifficulty)
                    .build();

            employees.add(newEmployee);
        }
    }

    public void addTasksToEmployeesWithoutTasks(){
        employees.forEach(employee -> {
            if(employee.getTasks().size() == 0){
                ArrayList<EmployeeTask> tasksForEmployee = new ArrayList<EmployeeTask>(tasks);
                employee.setTasks(tasksForEmployee);
            }
        });
    }

    public void dismissJob(){
        employees.removeIf(employee -> !employee.isHired());
    }

    public boolean checkIfOfficeIsEmpty(){
        if(employees.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

}
