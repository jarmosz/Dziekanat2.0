package com.jarmosz.Dziekanat20.applicants.person;
import com.jarmosz.Dziekanat20.applicants.ApplicantType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
