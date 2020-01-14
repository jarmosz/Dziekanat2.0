package com.jarmosz.Dziekanat20.applicants;

import com.jarmosz.Dziekanat20.applicants.person.*;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ApplicantsCreator {

    public static Person createApplicant(ApplicantType type) {

        switch(type){
            case STUDENT:
                Student newStudent = Student.builder()
                        .type(ApplicantType.STUDENT)
                        .hasChocolate(generateRandomBoolean())
                        .hasFlowers(generateRandomBoolean())
                        .drinkedBears(0)
                        .build();

                if(newStudent.isHasChocolate() && newStudent.isHasFlowers()){
                    newStudent.setPriority(4.5);
                }
                else if(newStudent.isHasChocolate() && !newStudent.isHasFlowers()){
                    newStudent.setPriority(3.5);
                }
                else {
                    newStudent.setPriority(1.0);
                }

                return newStudent;

            case PROFESSOR:
                Professor newProfessor = Professor.builder()
                        .type(ApplicantType.PROFESSOR)
                        .priority(5.0)
                        .totalDifferential(0)
                        .build();
                return newProfessor;

            case PHDSTUDENT:
                PhDStudent newPhDStudent = PhDStudent.builder()
                        .type(ApplicantType.PHDSTUDENT)
                        .priority(2.0)
                        .downgradeMarkTotal(0.0)
                        .build();
                return newPhDStudent;

            case LECTURER:
                Lecturer newLecturer = Lecturer.builder()
                        .type(ApplicantType.LECTURER)
                        .priority(4.0)
                        .additionalHomeworkTasks(0)
                        .build();
                return newLecturer;

            case FRIEND:
                Friend newFriend = Friend.builder()
                        .type(ApplicantType.FRIEND)
                        .priority(3.0)
                        .complainTime(0.0)
                        .build();
                return newFriend;

            case DEAN:
                Dean newDean = Dean.builder()
                        .type(ApplicantType.DEAN)
                        .priority(6.0)
                        .build();
                return newDean;

            default:
                throw new IllegalArgumentException("Unknown person");
        }
    }


    private static boolean generateRandomBoolean(){
        Random random = new Random();
        int randomNumber = random.nextInt(2);

        switch(randomNumber){
            case 0:
                return true;

            case 1:
                return false;

            default:
                return true;
        }
    }
}
