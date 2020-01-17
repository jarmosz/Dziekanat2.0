package com.jarmosz.Dziekanat20.applicants.person;
import com.jarmosz.Dziekanat20.applicants.ApplicantType;
import com.jarmosz.Dziekanat20.applicants.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Builder
@ToString
@Getter
@Setter
public class Lecturer implements Person {


    @Builder.Default
    private double priority;
    private int additionalHomeworkTasks;
    private ApplicantType type;


    @Override
    public void reactOnWaiting() {
        this.additionalHomeworkTasks += 1;
    }

    @Override
    public String tellSomethingAbout() {
        return toString();
    }

    @Override
    public ApplicantType sayType() {
        return this.type;
    }

    @Override
    public double howIAmImportantHere() {
        return this.priority;
    }
}
