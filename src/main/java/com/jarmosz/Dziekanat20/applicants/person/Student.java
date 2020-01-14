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
public class Student implements Person {

    @Builder.Default
    private double priority;
    private ApplicantType type;
    private int drinkedBears;
    private boolean hasChocolate;
    private boolean hasFlowers;

    @Override
    public void reactOnWaiting() {
        this.drinkedBears += 1;
    }

    @Override
    public String tellSomethingAbout() {
        return toString();
    }
}
