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
            return getPerson(iterator.next());
        }
        else if(!iterator.hasNext()){
            iterator = applicantsList.iterator();
            return getPerson(iterator.next());
        }
        else {
            throw new IllegalArgumentException("Person type, passed in properties doesn't exist!");
        }
    }

    public Person getPerson(ApplicantType applicantType){
        switch (applicantType) {
            case STUDENT:
                return applicantsFactory.student();
            case PROFESSOR:
                return applicantsFactory.professor();
            case PHDSTUDENT:
                return applicantsFactory.phdstudent();
            case LECTURER:
                return applicantsFactory.lecturer();
            case FRIEND:
                return applicantsFactory.friend();
            case DEAN:
                return applicantsFactory.dean();
            default:
                throw new IllegalArgumentException("Person type, passed in properties doesn't exist!");
        }

    }
}
