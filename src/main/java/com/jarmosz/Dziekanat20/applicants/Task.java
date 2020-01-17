package com.jarmosz.Dziekanat20.applicants;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Task {
    @Builder.Default
    @Getter
    @Setter
    private double difficulty;
}
