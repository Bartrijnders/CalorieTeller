package models;

import java.time.LocalDate;

public class FakeDateProvider implements DateProvider {

    private final LocalDate localDate;

    public FakeDateProvider(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public LocalDate GetCurrentDate() {
        return localDate;
    }
}
