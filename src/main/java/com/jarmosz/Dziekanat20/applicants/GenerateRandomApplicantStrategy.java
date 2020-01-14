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
    public Person generateApplicant() {
        switch(getApplicantTypeWithProbability()){
            case STUDENT:
                log.info("New Student created in generator");
                return applicantsFactory.student();
            case PROFESSOR:
                log.info("New Professor created in generator");
                return applicantsFactory.professor();
            case PHDSTUDENT:
                log.info("New PhDStudent created in generator");
                return applicantsFactory.phdstudent();
            case LECTURER:
                log.info("New Lecturer created in generator");
                return applicantsFactory.lecturer();
            case FRIEND:
                log.info("New Employee's Friend created in generator");
                return applicantsFactory.friend();
            case DEAN:
                log.info("New Dean created in generator");
                return applicantsFactory.dean();
            default:
                throw new IllegalArgumentException("Unknown enum type returned in random applicant generator");
        }
    }

}
