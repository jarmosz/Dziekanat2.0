package com.jarmosz.Dziekanat20.applicants;

import com.jarmosz.Dziekanat20.applicants.person.Person;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;

@Component
@Log
public class GenerateConfiguredApplicantStrategy implements GenerateStrategy {

    @Autowired
    private ApplicantsFactory applicantsFactory;


    @Value("#{'${applicants.queue}'.split(' ')}")
    private ArrayList<ApplicantType> applicantsList;

    private Iterator<ApplicantType> iterator;

    @PostConstruct
    private void initIterator(){
        iterator = applicantsList.iterator();
    }

    @Override
    public Person generatePerson() {

        if (iterator.hasNext()) {
            switch (iterator.next()) {
                case STUDENT:
                    log.info("New Student created from properties");
                    return applicantsFactory.student();
                case PROFESSOR:
                    log.info("New Professor created from properties");
                    return applicantsFactory.professor();
                case PHDSTUDENT:
                    log.info("New PhDStudent created from properties");
                    return applicantsFactory.phdstudent();
                case LECTURER:
                    log.info("New Lecturer created from properties");
                    return applicantsFactory.lecturer();
                case FRIEND:
                    log.info("New Employee's Friend created from properties");
                    return applicantsFactory.friend();
                case DEAN:
                    log.info("New Dean created from properties");
                    return applicantsFactory.dean();
                default:
                    throw new IllegalArgumentException("Person type, passed in properties doesn't exist!");
            }
        }
        else {
            throw new IllegalArgumentException("Person type, passed in properties doesn't exist!");
        }
    }
}
