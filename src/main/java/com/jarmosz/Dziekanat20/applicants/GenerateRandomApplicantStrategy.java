package com.jarmosz.Dziekanat20.applicants;

import com.jarmosz.Dziekanat20.applicants.person.Person;
import lombok.extern.java.Log;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log
public class GenerateRandomApplicantStrategy implements GenerateStrategy {

    @Autowired
    private ApplicantsFactory applicantsFactory;

    private ApplicantType getApplicantTypeWithProbability(){
        List<Pair<ApplicantType, Double>> probabilities = new ArrayList<Pair<ApplicantType, Double>>();

        probabilities.add(new Pair(ApplicantType.STUDENT, 0.65));
        probabilities.add(new Pair(ApplicantType.PHDSTUDENT, 0.07));
        probabilities.add(new Pair(ApplicantType.FRIEND, 0.15));
        probabilities.add(new Pair(ApplicantType.LECTURER, 0.06));
        probabilities.add(new Pair(ApplicantType.PROFESSOR, 0.05));
        probabilities.add(new Pair(ApplicantType.DEAN, 0.02));

        EnumeratedDistribution<ApplicantType> distribution = new EnumeratedDistribution<ApplicantType>(probabilities);

        ApplicantType randomType = distribution.sample();

        return randomType;
    }

    @Override
    public Person generatePerson() {
        switch(getApplicantTypeWithProbability()){
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
                throw new IllegalArgumentException("Unknown enum type returned in random applicant generator");
        }
    }

}
