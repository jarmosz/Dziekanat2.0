package com.jarmosz.Dziekanat20.applicants.person;

import com.jarmosz.Dziekanat20.applicants.ApplicantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Person {
    public void reactOnWaiting();
    public String tellSomethingAbout();
    public ApplicantType sayType();
    public double howIAmImportantHere();
}
