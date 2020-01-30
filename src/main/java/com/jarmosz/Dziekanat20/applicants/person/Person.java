package com.jarmosz.Dziekanat20.applicants.person;

import com.jarmosz.Dziekanat20.applicants.ApplicantType;

public interface Person {
    public void reactOnWaiting();
    public String tellSomethingAbout();
    public ApplicantType sayType();
    public double howIAmImportantHere();
    public void waitOneMoreRound();
}
