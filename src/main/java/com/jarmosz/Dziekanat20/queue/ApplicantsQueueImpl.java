package com.jarmosz.Dziekanat20.queue;

import com.jarmosz.Dziekanat20.applicants.Applicant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
@Setter
public class ApplicantsQueueImpl {
    private ApplicantsQueue<Applicant> applicantsQueue;

    @PostConstruct
    private void createQueue(){
        applicantsQueue = new ApplicantsQueue<Applicant>();
    }


}
