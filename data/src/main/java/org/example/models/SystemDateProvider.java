package org.example.models;

import java.time.Clock;
import java.time.LocalDate;

public class SystemDateProvider implements DateProvider {

    private final Clock clock;

    public SystemDateProvider(Clock clock) {
        this.clock = clock;
    }

    @Override
    public LocalDate GetCurrentDate() {
        return LocalDate.now(clock);
    }
}
