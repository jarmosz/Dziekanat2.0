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
public class PhDStudent implements Person {


    @Builder.Default
    private double priority;
    private double downgradeMarkTotal;
    private ApplicantType type;


    @Override
    public void reactOnWaiting() {
        this.downgradeMarkTotal += 0.5;
    }

    @Override
    public String tellSomethingAbout() {
        return toString();
    }
}
