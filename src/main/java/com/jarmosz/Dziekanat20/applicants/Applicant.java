package com.jarmosz.Dziekanat20.applicants;

import com.jarmosz.Dziekanat20.applicants.person.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Applicant {

    private Person personality;
    private ArrayList<Task> tasks;

}
