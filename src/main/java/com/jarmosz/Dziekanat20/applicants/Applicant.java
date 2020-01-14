package com.jarmosz.Dziekanat20.applicants;

import com.jarmosz.Dziekanat20.applicants.person.Person;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Applicant {

    @Autowired
    private GenerateStrategyFactory strategyFactory;

    @Getter
    private Person personality;

    public void generatePersonality(){
        personality =  strategyFactory.getStrategy().generateApplicant();
    }

}
