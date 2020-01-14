package com.jarmosz.Dziekanat20.applicants;

import com.jarmosz.Dziekanat20.applicants.person.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicantsFactory {

    @Bean
    public Person student(){
        return ApplicantsCreator.createApplicant(ApplicantType.STUDENT);
    }

    @Bean
    public Person professor(){
        return ApplicantsCreator.createApplicant(ApplicantType.PROFESSOR);
    }

    @Bean
    public Person phdstudent(){
        return ApplicantsCreator.createApplicant(ApplicantType.PHDSTUDENT);
    }

    @Bean
    public Person lecturer(){
      return ApplicantsCreator.createApplicant(ApplicantType.LECTURER);
    }

    @Bean
    public Person friend(){
        return ApplicantsCreator.createApplicant(ApplicantType.FRIEND);
    }

    @Bean
    public Person dean(){
        return ApplicantsCreator.createApplicant(ApplicantType.DEAN);
    }
}
